
package io.javabrains.resume_portal;

import io.javabrains.resume_portal.util.jwtutil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private jwtutil jwtutil;

    @Autowired
    private UserDetailsService userDetailsService;


    public JwtFilter(jwtutil jwtutil, UserDetailsService userDetailsService) {
        this.jwtutil = jwtutil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie c:cookies){
                if(c.getName().equals("jwt")){
                    token = c.getValue();
                    try {
                        username = jwtutil.extractUsername(token);
                    } catch (Exception e) {
                        username = null;  // invalid or missing token
                    }
                }
            }
        }

//        if(authHeader != null && authHeader.startsWith("Bearer ")){
//            token = authHeader.substring(7);
//            username = jwtutil.extractUsername(token);
//        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails UserDetails = this.userDetailsService.loadUserByUsername(username);
            if(jwtutil.validateToken(token,UserDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        UserDetails, null, UserDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

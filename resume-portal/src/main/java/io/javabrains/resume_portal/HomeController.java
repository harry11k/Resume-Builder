package io.javabrains.resume_portal;

import io.javabrains.resume_portal.model.AuthenticateRequest;
import io.javabrains.resume_portal.model.AuthenticateResponse;
import io.javabrains.resume_portal.util.jwtutil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private jwtutil jwtutil;
    private AuthenticationManager authenticationManager;
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/welcome")
    public String welcome(){
        return "<h1>WELCOME</h1>";
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(),authenticateRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials", e);
        }

        final String jwt = jwtutil.generateToken(myUserDetailsService.loadUserByUsername(authenticateRequest.getUsername()));

        return ResponseEntity.ok(new AuthenticateResponse(jwt));
    }

}

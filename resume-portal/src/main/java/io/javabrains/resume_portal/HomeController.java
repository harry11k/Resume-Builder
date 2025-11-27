package io.javabrains.resume_portal;

import io.javabrains.resume_portal.Repository.UserProfileRepository;
import io.javabrains.resume_portal.model.Education;
import io.javabrains.resume_portal.model.Job;
import io.javabrains.resume_portal.model.UserProfile;
import io.javabrains.resume_portal.util.jwtutil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private jwtutil jwtutil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/edit")
    public String edit(Model model, @CookieValue(value = "jwt") String jwt, @RequestParam(required = false) String add){
        if (jwt == null) {
            return "redirect:/login";
        }
        String userId = jwtutil.extractUsername(jwt);
        model.addAttribute("userId",userId);
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found " + userId));
        UserProfile userprofile = userProfileOptional.get();
        if("job".equals(add)){
            userprofile.getJobs().add(new Job());
        }else if("education".equals(add)){
            userprofile.getEducations().add(new Education());
        }else if("skill".equals(add)){
            userprofile.getSkills().add("");
        }
        model.addAttribute("userProfile",userprofile);
        return "profile-edit";
    }

    @GetMapping("/delete")
    public String delete(Model model, @CookieValue(value = "jwt") String jwt, @RequestParam String type,@RequestParam int index){
//        String userId = principal.getName();
        String userId = jwtutil.extractUsername(jwt);
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found " + userId));
        UserProfile userprofile = userProfileOptional.get();
        if("job".equals(type)){
            userprofile.getJobs().remove(index);
        }else if("education".equals(type)){
            userprofile.getEducations().remove(index);
        }else if("skill".equals(type)){
            userprofile.getSkills().remove(index);
        }
        userProfileRepository.save(userprofile);
        return "redirect:/edit";
    }

    @PostMapping("/edit")
    public String postEdit(Model model, @CookieValue(value = "jwt", required = false) String jwt, @ModelAttribute UserProfile userProfile){
        String user_name = jwtutil.extractUsername(jwt);
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(user_name);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found "+ user_name));
        UserProfile savedUsedProfile = userProfileOptional.get();
        userProfile.setId(savedUsedProfile.getId());
        userProfile.setUserName(user_name);
        userProfileRepository.save(userProfile);
        return "redirect:/view/" + user_name;
    }

    @GetMapping("/view/{userId}")
    public String view(@CookieValue(value = "jwt",required = false) String jwt, @PathVariable String userId, Model model){
        if(jwt != null && !jwtutil.extractUsername(jwt).isEmpty()){
            boolean currentUserProfile = jwtutil.extractUsername(jwt).equals(userId);
            model.addAttribute("currentUserProfile",currentUserProfile);
        }
        String userName = jwtutil.extractUsername(jwt);
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found " + userId));
        model.addAttribute("userId",userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile",userProfile);
        System.out.println(userProfile.getJobs());

        return "profile-templates/" + userProfile.getTheme() + "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestParam("user_name") String username,@RequestParam("password") String password
            , HttpServletResponse response) throws Exception {
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials", e);
        }

        final String jwt = jwtutil.generateToken(myUserDetailsService.loadUserByUsername(username));

        ResponseCookie cookie = ResponseCookie.from("jwt", jwt)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(3600)
                .build();

        response.setHeader("Set-Cookie", cookie.toString());

//      return ResponseEntity.ok(new AuthenticateResponse(jwt));
        return "redirect:/edit";
    }

}


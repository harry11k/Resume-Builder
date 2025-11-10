package io.javabrains.resume_portal;

import io.javabrains.resume_portal.Repository.UserRepository;
import io.javabrains.resume_portal.model.MyUserDetails;
import io.javabrains.resume_portal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String UserName){
        Optional<User> user = userRepository.findByuserName(UserName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: "+UserName));

        return user.map(MyUserDetails::new).get();
    }
}

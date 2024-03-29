package com.energygrid.auth;

import com.energygrid.auth.common.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //TODO: Remove debug line

        if (email.isEmpty()) {
            throw new IllegalArgumentException("CustomerCode can't be empty");
        }
        User user = restTemplate.getForObject("http://10.93.12.126:9000/user/user?email=" + email, User.class);
//        User user = restTemplate.getForObject("http://localhost:9000/user/user?email=" + email, User.class);
        return user;
    }


}

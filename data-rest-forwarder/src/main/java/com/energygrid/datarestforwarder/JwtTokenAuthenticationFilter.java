package com.energygrid.datarestforwarder;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {


    public JwtTokenAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {


        //the user does not have to be authenticated for this service
        chain.doFilter(request, response);        // If not valid, go to the next filter.
        return;
    }
}

package com.example.demo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println("Token: " + token);

        if (token != null) {
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                Authentication authentication = new FirebaseAuthenticationToken(decodedToken.getUid(), token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println(authentication.getName());
                System.out.println(authentication.isAuthenticated());
                System.out.println(authentication.getPrincipal());
                System.out.println(authentication.getCredentials());
                System.out.println(authentication.getDetails());
                System.out.println(authentication.getAuthorities());

            } catch (Exception e) {
                throw new RuntimeException("Failed to verify token", e);
            }
        }

        filterChain.doFilter(request, response);
    }
}

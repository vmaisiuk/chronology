package com.andersen.chronology.auth.config;

import com.andersen.chronology.auth.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class BearerTokenInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = (Authentication) request.getUserPrincipal();

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails userDetails) {
                response.addHeader(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(userDetails));
            } else if (authentication.getPrincipal() instanceof DefaultOidcUser oidcUser) {
                response.addHeader(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(oidcUser));
            }
        }
    }
}

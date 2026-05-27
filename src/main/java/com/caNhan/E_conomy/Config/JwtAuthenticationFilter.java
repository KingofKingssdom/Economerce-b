package com.caNhan.E_conomy.Config;

import com.caNhan.E_conomy.Entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtTokenProvider tokenProvider;
    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {


                String username = tokenProvider.getUsernameFromJWT(jwt);
                Long userId = tokenProvider.getUserIdFromJWT(jwt);
                String fullName = tokenProvider.getFullNameFromJWT(jwt);
                User principalUser = new User();
                principalUser.setEmail(username);
                principalUser.setId(userId);
                principalUser.setFullName(fullName);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(principalUser, null, Collections.emptyList());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Không thể xác thực người dùng dựa trên JWT", ex);
        }


        filterChain.doFilter(request, response);
    }
    private String getJwtFromRequest(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()){
                if ("accessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }

        }
        return null;
    }

}

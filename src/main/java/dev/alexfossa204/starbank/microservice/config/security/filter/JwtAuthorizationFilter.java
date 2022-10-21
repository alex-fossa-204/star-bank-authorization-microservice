package dev.alexfossa204.starbank.microservice.config.security.filter;

import dev.alexfossa204.starbank.microservice.config.security.provider.JwtTokenProvider;
import dev.alexfossa204.starbank.microservice.service.ApiUserDetailsService;
import dev.alexfossa204.starbank.microservice.config.security.constant.JwtTokenConfigurationConstant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpHeaders.*;
import static  org.springframework.http.HttpMethod.*;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final ApiUserDetailsService apiUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isRequestMethodEqualsOptions = request.getMethod().equalsIgnoreCase(OPTIONS.name());
        if(isRequestMethodEqualsOptions) {
            response.setStatus(OK.value());
        }
        if(!isRequestMethodEqualsOptions) {
            String requestAuthHeader = request.getHeader(AUTHORIZATION);
            if (StringUtils.isBlank(requestAuthHeader) || !requestAuthHeader.startsWith(JwtTokenConfigurationConstant.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = requestAuthHeader.substring(JwtTokenConfigurationConstant.TOKEN_PREFIX.length());
            String username = jwtTokenProvider.getSubjectUsername(token);
            apiUserDetailsService.loadUserByUsername(username);
            boolean isTokenValidAndAuthenticationIsNull = jwtTokenProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication() == null;
            if(isTokenValidAndAuthenticationIsNull) {
                List<GrantedAuthority> grantedAuthorities = jwtTokenProvider.getAuthorities(token);
                Authentication authentication = jwtTokenProvider.getAuthentication(username, grantedAuthorities, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            if(!isTokenValidAndAuthenticationIsNull) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}

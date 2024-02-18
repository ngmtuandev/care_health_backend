package com.care_health.care_health.configurations;

import com.care_health.care_health.constant.SystemConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Data
@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtAuthenticationsFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    CustomUserDetailService customUserDetailService;


    private String getJwtFromRequest(HttpServletRequest request) {

        log.info("JwtProvider autowired: " + jwtProvider);

        String bearerToken = request.getHeader(SystemConstant.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken)  &&  bearerToken.startsWith(SystemConstant.BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /* Config error cors */

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, x-customer-header-1, x-customer-header-2, Authorization");


        try {

            // get jwt from request
            String getJWT = getJwtFromRequest(request);
            if (StringUtils.hasText(getJWT) &&  jwtProvider.validateToken(getJWT)) {

                // get userName from jwt
                String userNameCurrent = jwtProvider.getUserNameFromJWT(getJWT);

                // get info detail of user current (all info)
                // security just work with UserDetails
                UserDetails userDetails = customUserDetailService.loadUserByUsername(userNameCurrent);
                System.out.println("jwt authentication filter ----> ");
                if (userDetails != null) {

                    // SUCCESS => set info user for security and provider all role user -> provider for context security
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request)
                    );

                    // set in security -> in contextHolder
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

                System.out.println("set password success");

            }


        } catch (Exception e) {
            log.error("fail authentication " + e);
        }

        // filter when hava a request
        filterChain.doFilter(request, response);

    }
}

package com.smartcode.ecommerce.configuration.security.jwt;

import com.smartcode.ecommerce.model.user.details.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.ObjectUtils.isEmpty;


@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {


    private final JwtTokenProvider jwtTokenProvider;




    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final var token = header.split(" ")[1].trim();
        if (!jwtTokenProvider.validateJwtTokenSignature(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        var userDetails = MyUserDetails.build(
                jwtTokenProvider.getId(token),
                jwtTokenProvider.getUsername(token),
                null,
                jwtTokenProvider.getRole(token)
        );

        var authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }





}
package com.userSecurityLearning.userSecurityLearning.jwtImp;

import com.userSecurityLearning.userSecurityLearning.jwtImp.jwtService.JwtUserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    JwtUserService jwtUserService;
    public JwtFilter(JwtUserService jwtUserService){
        this.jwtUserService=jwtUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWT Filter Called");
        String authHeader=request.getHeader("Authorization");
        String token=null;
        if(authHeader!=null && authHeader.startsWith("Beare")){
            token=authHeader.substring(7);
        }
//        System.out.println("token "+token);
        if(token!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            try {
                Claims claims = jwtUserService.verifySignatureAnExtractClaims(token);
                String role=claims.get("Role",String.class);
                List<SimpleGrantedAuthority>roleAuth=List.of(new SimpleGrantedAuthority(role));
//                System.out.println("Role from JWT = " + role);
//                System.out.println("Authority = " + roleAuth);
                if(!jwtUserService.isExpired(token)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(claims.getSubject(), null, roleAuth);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                System.out.println("Invalid JWT: " + e.getMessage());
            }
        }

        filterChain.doFilter(request,response);

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.sec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author MEDION
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

      private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SecurityConstants.SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
      
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain fc) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, "
                + "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, "
                + "authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, "
                + "Access-Control-Allow-Credentials, Authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
            if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                fc.doFilter(request, response);
                return;
            }
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
            String username = claims.getSubject();
            ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
            Collection<GrantedAuthority> authoritys = new ArrayList<>();
            roles.forEach(r -> {
                authoritys.add(new SimpleGrantedAuthority(r.get("authority")));
            });
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(username, null, authoritys);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            fc.doFilter(request, response);
        }
    }

}

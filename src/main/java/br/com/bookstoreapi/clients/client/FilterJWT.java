package br.com.bookstoreapi.clients.client;

import br.com.bookstoreapi.clients.client.service.ServiceJWT;
import br.com.bookstoreapi.clients.client.service.ServiceJWTImpl;
import io.jsonwebtoken.*;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterJWT extends GenericFilterBean {

    public final static int TOKEN_INDEX = 7;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Token inexistente ou mal formatado!");
            return;
            // throw new ServletException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho (removendo o prefixo "Bearer ").
        String token = header.substring(TOKEN_INDEX);
        try {
            Jwts.parser().setSigningKey(ServiceJWTImpl.TOKEN_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | MalformedJwtException | PrematureJwtException
                 | UnsupportedJwtException | IllegalArgumentException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }

}

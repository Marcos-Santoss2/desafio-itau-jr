package br.com.desafio.itau.crud.security;

import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
import br.com.desafio.itau.crud.services.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extraiTokenHeader(request);

        if (token != null){
            String login = autenticacaoService.validaTokenJwt(token);
            UsuarioModel usuario = repository.findBylogin(login).get();

            var autentication = new UsernamePasswordAuthenticationToken(usuario , null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autentication);

        }

        filterChain.doFilter(request, response);

    }


    public String extraiTokenHeader(HttpServletRequest request){
        var authHeather = request.getHeader("Authorization");

        if (authHeather == null){
            return null;
        }

        if (!authHeather.split(" ")[0].equals("Bearer")) {
            return null;
        }
        return authHeather.split(" ")[1];
    }


}

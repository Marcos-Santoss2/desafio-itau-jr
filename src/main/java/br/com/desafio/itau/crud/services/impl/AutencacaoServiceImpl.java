package br.com.desafio.itau.crud.services.impl;

import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
import br.com.desafio.itau.crud.services.AutenticacaoService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
@Transactional
@Service
public class AutencacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private final UsuarioRepository repository;

    public AutencacaoServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = repository.findBylogin(login);

        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario ["+usuario+"] não encontrado");
        }

        return usuario.get();
    }

    @Override
    public String obterToken(UsuarioModel usuarioAuth) {
        UsuarioModel usuario =  repository.findBylogin(usuarioAuth.getLogin()).get();

        return geraTokenJwt(usuario);
    }

    public String geraTokenJwt(UsuarioModel usuario){

        try {
            Algorithm algorithm = Algorithm.HMAC256("segredo-sem-ser-segredo");

            return JWT.create()
                    .withIssuer("desafio-itau")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(geraDataExpericao())
                    .sign(algorithm);
        }catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar o token!" + exception.getMessage());
        }
    }

    private Instant geraDataExpericao() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    @Override
    public String validaTokenJwt(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("segredo-sem-ser-segredo");

            return JWT.require(algorithm)
                    .withIssuer("desafio-itau")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){

            return "";
        }

    }
}

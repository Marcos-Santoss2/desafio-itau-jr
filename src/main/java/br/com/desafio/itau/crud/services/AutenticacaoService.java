package br.com.desafio.itau.crud.services;

import br.com.desafio.itau.crud.model.UsuarioModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {

    public String obterToken(UsuarioModel usuario);

    public String validaTokenJwt(String token);
}

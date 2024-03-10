package br.com.desafio.itau.crud.controller;

import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth (@RequestBody UsuarioModel authUsuario){
        var usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(authUsuario.getLogin(), authUsuario.getSenha());


        authenticationManager.authenticate(usuarioAutenticationToken);

        return autenticacaoService.obterToken(authUsuario);
    }
}

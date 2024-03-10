package br.com.desafio.itau.crud.controller;

import br.com.desafio.itau.crud.model.DesejosModel;
import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.DesejoRepository;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
import br.com.desafio.itau.crud.services.UsuarioService;
import br.com.desafio.itau.crud.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listarTodos(){

        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario ){

        return  ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }

    @PostMapping("/{login}/operacaoValor")
    public ResponseEntity<String> operacaoValor(@PathVariable(name = "login") String login,
                                                @RequestParam int operacao,
                                                @RequestParam Double valorOperacao) {

        return ResponseEntity.ok(usuarioService.operacaoValor(login, operacao, valorOperacao));
    }

}

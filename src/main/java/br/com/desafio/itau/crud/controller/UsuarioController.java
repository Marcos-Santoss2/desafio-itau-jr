package br.com.desafio.itau.crud.controller;

import br.com.desafio.itau.crud.model.DesejosModel;
import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.DesejoRepository;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
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

    private final UsuarioRepository repository;
    private final DesejoRepository repositoryDesejo;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository repository, DesejoRepository repositoryDesejo, PasswordEncoder encoder) {
        this.repository = repository;
        this.repositoryDesejo = repositoryDesejo;
        this.encoder = encoder;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos(){

        List<UsuarioModel> lista = repository.findAll();

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario ){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return  ResponseEntity.ok(repository.save(usuario));
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha (@RequestParam String login,
                                                 @RequestParam String senha) {

        Optional<UsuarioModel> optUsuario = repository.findBylogin(login);

        if(optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        UsuarioModel usuario = optUsuario.get();

        boolean valid = encoder.matches(senha, usuario.getSenha());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }







}

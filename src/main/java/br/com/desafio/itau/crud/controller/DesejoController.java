package br.com.desafio.itau.crud.controller;

import br.com.desafio.itau.crud.model.DesejosModel;
import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.DesejoRepository;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
import br.com.desafio.itau.crud.services.DesejoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuario/")
public class DesejoController {


    @Autowired
    DesejoService desejoService;


    @GetMapping("/{login}/desejos/listar")
    public ResponseEntity<List<DesejosModel>> listarDesejo(@PathVariable(name = "login") String login) {

        return ResponseEntity.ok(desejoService.listarDesejo(login));
    }

    @PostMapping("/{login}/desejos/adicionar")
    public ResponseEntity<String> adicionarDesejos(@PathVariable(name = "login") String login,
                                                   @RequestParam String objetivo,
                                                   @RequestParam Double valor) {

        return ResponseEntity.ok(desejoService.adicionarDesejos(login, objetivo, valor));

    }

    @DeleteMapping("/{login}/desejos/deletar")
    public ResponseEntity<String> deletarDesejos(@PathVariable(name = "login") String login,
                                                 @RequestParam Integer idDesejo) {

        return ResponseEntity.ok(desejoService.deletarDesejos(idDesejo));
    }

    @PutMapping("/{login}/desejos/modificar")
    public ResponseEntity<DesejosModel> modificarDesejo(@PathVariable(name = "login") String login,
                                                       @RequestParam Integer idDesejo,
                                                       @RequestBody DesejosModel desejoNovo) {

        return ResponseEntity.ok().body(desejoService.modificarDesejo(idDesejo, desejoNovo));
    }

}

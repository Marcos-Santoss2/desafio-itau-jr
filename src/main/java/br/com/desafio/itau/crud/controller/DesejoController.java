package br.com.desafio.itau.crud.controller;

import br.com.desafio.itau.crud.model.DesejosModel;
import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.DesejoRepository;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
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

    private final UsuarioRepository repository;
    private final DesejoRepository repositoryDesejo;


    public DesejoController(UsuarioRepository repository, DesejoRepository repositoryDesejo, PasswordEncoder encoder) {
        this.repository = repository;
        this.repositoryDesejo = repositoryDesejo;
    }

    @GetMapping("/{login}/listaDesejos")
    public ResponseEntity<List<DesejosModel>> listarDesejo(@PathVariable(name = "login") String login){

        Optional<UsuarioModel> desejo = repository.findBylogin(login);

        return ResponseEntity.ok(desejo.get().getDesejo());
    }
    @PostMapping("/{login}/addDesejo")
    public ResponseEntity<String> addDesejos (@PathVariable(name="login") String login,
                                              @RequestParam String objetivo,
                                              @RequestParam Double valor){

        Optional<UsuarioModel> optUsuario = repository.findBylogin(login);

        if(optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        UsuarioModel usuario = optUsuario.get();

        List<DesejosModel> novoDesejo = new ArrayList<>();

        DesejosModel desejosModel = new DesejosModel(objetivo, valor);

        desejosModel.setUsuario(usuario);

        novoDesejo.add(desejosModel);

        usuario.setDesejo(novoDesejo);

        repository.save(usuario);

        return ResponseEntity.ok().body("Objetivo Registrado");

    }
    @GetMapping("/{login}/buscaDesejos")
    public ResponseEntity<List<DesejosModel>>buscaDesejo (@PathVariable(name = "login") String login){

        Optional<UsuarioModel> optUsuario = repository.findBylogin(login);

        if(optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<DesejosModel>());
        }

        UsuarioModel usuario = optUsuario.get();

        return ResponseEntity.ok().body(usuario.getDesejo());

    }

    @DeleteMapping("/{login}/deletaDesejos/{idDesejo}")
    public ResponseEntity<String> deletaDesejos(@PathVariable(name = "login") String login,
                                                @PathVariable Integer idDesejo){

        Optional<DesejosModel> delete = repositoryDesejo.findById(idDesejo);

        repositoryDesejo.delete(delete.get());

        return ResponseEntity.ok().body("Desejo deletado com sucesso!!");
    }
    @PostMapping("/{login}/OperacaoValor")
    public ResponseEntity <String> operacaoValor(@PathVariable(name = "login") String login,
                                                 @RequestParam int operacao,
                                                 @RequestParam Double valorOperacao){

        Optional<UsuarioModel> usuario = repository.findBylogin(login);
        Double valor = usuario.get().getValorTotal();

        if(valorOperacao.isNaN()) usuario.get().setValorTotal(0.0) ;

        switch (operacao){
            case 1 -> {
                Double adicao = valor + valorOperacao;
                usuario.get().setValorTotal(adicao);
                repository.save(usuario.get());
                return ResponseEntity.ok().body("Valor Adicionado!! \t Novo valor: " + adicao.toString());
            }
            case 2 -> {
                Double subtracao = valor - valorOperacao;
                usuario.get().setValorTotal(subtracao);
                repository.save(usuario.get());
                return ResponseEntity.ok().body("Valor subtraido!! \t Novo valor: " + subtracao.toString());
            }
            default -> {
                return ResponseEntity.badRequest().body("Operação Inválida");
            }

        }

    }
    @PutMapping("/{login}/modificaDesejo")
    public ResponseEntity<DesejosModel> modificaDesejo(@PathVariable(name="login") String login,
                                                 @RequestBody Integer idDesejo,
                                                 @RequestBody DesejosModel desejoNov){

        Optional<DesejosModel> desejoMod = repositoryDesejo.findById(idDesejo);

        desejoMod.get().setObjetivo(desejoNov.getObjetivo());

        desejoMod.get().setValor(desejoNov.getValor());

        repositoryDesejo.save(desejoMod.get());


        return ResponseEntity.ok().body(desejoMod.get());
    }

}

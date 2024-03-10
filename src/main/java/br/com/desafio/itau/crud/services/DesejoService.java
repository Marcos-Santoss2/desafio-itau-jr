package br.com.desafio.itau.crud.services;

import br.com.desafio.itau.crud.model.DesejosModel;
import br.com.desafio.itau.crud.model.UsuarioModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface DesejoService {


    public List<DesejosModel> listarDesejo(String login);

    public String adicionarDesejos(String login, String objetivo, Double valor);

    public String deletarDesejos(Integer idDesejo);

    public DesejosModel modificarDesejo(Integer idDesejo, DesejosModel desejoNovo);


}

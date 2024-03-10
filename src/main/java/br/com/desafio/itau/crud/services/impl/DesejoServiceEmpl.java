package br.com.desafio.itau.crud.services.impl;

import br.com.desafio.itau.crud.model.DesejosModel;
import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.DesejoRepository;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
import br.com.desafio.itau.crud.services.DesejoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesejoServiceEmpl implements DesejoService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    DesejoRepository desejoRepository;

    @Override
    public List<DesejosModel> listarDesejo(String login) {

        return usuarioRepository.findBylogin(login).get().getDesejo();
    }

    @Override
    public String adicionarDesejos(String login, String objetivo, Double valor) {

        Optional<UsuarioModel> optUsuario = usuarioRepository.findBylogin(login);

        UsuarioModel usuario = optUsuario.get();

        List<DesejosModel> novoDesejo = new ArrayList<>();

        DesejosModel desejosModel = new DesejosModel(objetivo, valor);

        desejosModel.setUsuario(usuario);

        novoDesejo.add(desejosModel);

        usuario.setDesejo(novoDesejo);

        usuarioRepository.save(usuario);

        return "Desejo adicionado com sucesso!!!";
    }

    @Override
    public String deletarDesejos(Integer idDesejo) {

        Optional<DesejosModel> delete = desejoRepository.findById(idDesejo);

        desejoRepository.delete(delete.get());

        return "Desejo deletado com sucesso!!!";
    }

    @Override
    public DesejosModel modificarDesejo( Integer idDesejo, DesejosModel desejoNovo) {

        Optional<DesejosModel> desejoMod = desejoRepository.findById(idDesejo);

        desejoMod.get().setObjetivo(desejoNovo.getObjetivo());

        desejoMod.get().setValor(desejoNovo.getValor());

        return desejoRepository.save(desejoMod.get());
    }
}
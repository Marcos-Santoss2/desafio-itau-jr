package br.com.desafio.itau.crud.services;

import br.com.desafio.itau.crud.model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsuarioService {

    public List<UsuarioModel> listarTodos();

    public UsuarioModel salvarUsuario(UsuarioModel usuario);
}

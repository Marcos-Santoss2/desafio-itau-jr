package br.com.desafio.itau.crud.services.impl;

import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
import br.com.desafio.itau.crud.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private final PasswordEncoder encoder;

    public UsuarioServiceImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public List<UsuarioModel> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioModel salvarUsuario(UsuarioModel usuario) {

        String senhaEncriptada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncriptada);

        return usuarioRepository.save(usuario);
    }

}

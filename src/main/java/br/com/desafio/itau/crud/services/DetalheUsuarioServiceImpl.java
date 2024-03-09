package br.com.desafio.itau.crud.services;

import br.com.desafio.itau.crud.data.DetalheUsuarioData;
import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;
    public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = repository.findBylogin(username);

        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario ["+usuario+"] não encontrado");
        }

        return new DetalheUsuarioData(usuario);
    }
}

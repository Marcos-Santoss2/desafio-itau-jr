package br.com.desafio.itau.crud.repository;

import br.com.desafio.itau.crud.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    public Optional<UsuarioModel> findBylogin(String login);

}

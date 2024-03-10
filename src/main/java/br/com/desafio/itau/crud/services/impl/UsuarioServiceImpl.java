package br.com.desafio.itau.crud.services.impl;

import br.com.desafio.itau.crud.model.UsuarioModel;
import br.com.desafio.itau.crud.repository.UsuarioRepository;
import br.com.desafio.itau.crud.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Override
    public String operacaoValor(String login, int operacao, Double valorOperacao) {

        Optional<UsuarioModel> usuario = usuarioRepository.findBylogin(login);
        Double valor = usuario.get().getValorTotal();

        if (valorOperacao.isNaN()) usuario.get().setValorTotal(0.0);

        switch (operacao) {
            case 1 -> {
                Double adicao = valor + valorOperacao;
                usuario.get().setValorTotal(adicao);
                usuarioRepository.save(usuario.get());
                return "Valor Adicionado!! \t Novo valor: " + adicao.toString();
            }
            case 2 -> {
                Double subtracao = valor - valorOperacao;
                usuario.get().setValorTotal(subtracao);
                usuarioRepository.save(usuario.get());
                return "Valor subtraido!! \t Novo valor: " + subtracao.toString();
            }
            default -> {
                return "Operação Inválida";
            }

        }
    }

}

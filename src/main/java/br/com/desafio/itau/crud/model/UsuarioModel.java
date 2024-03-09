package br.com.desafio.itau.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer usuarioId;
    @Column(unique = true)
    private String login;

    private Double valorTotal;
    private String senha;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<DesejosModel> Desejo;


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public List<DesejosModel> getDesejo() {
        return Desejo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDesejo(List<DesejosModel> desejo) {
        Desejo = desejo;
    }


    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}


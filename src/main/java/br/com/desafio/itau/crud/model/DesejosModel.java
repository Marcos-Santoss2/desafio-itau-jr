package br.com.desafio.itau.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Desejos")
public class DesejosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer desejoId;

    private  String objetivo;
    private  Double valor;
    @ManyToOne
    @JoinColumn(name = "usuarioId")
    UsuarioModel usuario;


    public DesejosModel(String objetivo, Double valor) {
        this.objetivo = objetivo;
        this.valor = valor;
    }


    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Integer getDesejoId() {
        return desejoId;
    }
}

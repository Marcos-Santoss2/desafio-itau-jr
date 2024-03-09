package br.com.desafio.itau.crud.repository;

import br.com.desafio.itau.crud.model.DesejosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesejoRepository extends JpaRepository<DesejosModel, Integer> {

}

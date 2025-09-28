package br.com.alura.domain.repository;

import java.util.Optional;

import br.com.alura.domain.Agencia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AgenciaRepository implements PanacheRepository<Agencia> {

}

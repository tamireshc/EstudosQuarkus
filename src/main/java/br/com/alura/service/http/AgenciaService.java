package br.com.alura.service.http;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.domain.http.AgenciaHttp;
import br.com.alura.domain.repository.AgenciaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AgenciaService {

	@RestClient // Injeção do client REST definido na interface
	private SituacaoCadastralHttpService situacaoCadastralHttpService;

	private AgenciaRepository agenciaRepository;
	private MeterRegistry meterRegistry;

	AgenciaService(AgenciaRepository agenciaRepository, MeterRegistry meterRegistry) {
		this.agenciaRepository = agenciaRepository;
		this.meterRegistry = meterRegistry;
	}
	@Timed(value = "agencia.cadastrar.tempo", description = "Tempo de execução do cadastro de agência")
	@Transactional
	public void cadastrar(Agencia agencia) {
		agencia.id = null;      // força Hibernate a tratar como nova
		if (agencia.getEndereco() != null) {
			agencia.getEndereco().id = null; // força novo Endereco
		}
		Log.info("Agencia com cnpj: " + agencia.getCnpj() + "cadastrada.");
		meterRegistry.counter("agencia_adiciona_counter").increment();
		agenciaRepository.persist(agencia);
	}

	public Agencia buscarPorId(Integer id) {
		Agencia agencia = agenciaRepository.findById(id.longValue());
		if (agencia == null) {
			throw new AgenciaNaoAtivaOuNaoEncontradaException("Agência não encontrada");
		}
		return agencia;
	}

	@Transactional
	public void deletar(Integer id) {
		Agencia agencia = buscarPorId(id);
		if (agencia == null) {
			throw new AgenciaNaoAtivaOuNaoEncontradaException("Agência não encontrada");
		}
		agenciaRepository.delete(agencia);
	}

	@Transactional
	public void alterar(Agencia agencia) {
		Agencia agenciaExistente = buscarPorId(agencia.getId());
		if (agenciaExistente == null) {
			throw new AgenciaNaoAtivaOuNaoEncontradaException("Agência não encontrada");
		}
		agenciaExistente.setNome(agencia.getNome());
		agenciaExistente.setRazaoSocial(agencia.getRazaoSocial());
		agenciaRepository.persist(agenciaExistente);
	}
}

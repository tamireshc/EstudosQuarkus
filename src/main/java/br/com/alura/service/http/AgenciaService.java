package br.com.alura.service.http;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.domain.http.AgenciaHttp;
import br.com.alura.domain.repository.AgenciaRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgenciaService {

	@RestClient // Injeção do client REST definido na interface
	private SituacaoCadastralHttpService situacaoCadastralHttpService;

	private AgenciaRepository agenciaRepository;

	AgenciaService(AgenciaRepository agenciaRepository) {
		this.agenciaRepository = agenciaRepository;
	}

	public void cadastrar(Agencia agencia) {
		AgenciaHttp agenciaHttp = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());
		if (agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(agenciaHttp.getSituacaoCadastral().ATIVO)) {
			agenciaRepository.persist(agencia);
		} else {
			throw new AgenciaNaoAtivaOuNaoEncontradaException("Agência com situação cadastral inválida");
		}
	}

	public Agencia buscarPorId(Integer id) {
		Agencia agencia = agenciaRepository.findById(id.longValue());
		if (agencia == null) {
			throw new AgenciaNaoAtivaOuNaoEncontradaException("Agência não encontrada");
		}
		return agencia;
	}

	public void deletar(Integer id) {
		Agencia agencia = buscarPorId(id);
		if (agencia == null) {
			throw new AgenciaNaoAtivaOuNaoEncontradaException("Agência não encontrada");
		}
		agenciaRepository.delete(agencia);
	}

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

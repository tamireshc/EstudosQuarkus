package br.com.alura.service.http;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.domain.http.AgenciaHttp;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgenciaService {
	private List<Agencia> agencias = new ArrayList<>();

	@RestClient
	private SituacaoCadastralHttpService situacaoCadastralHttpService;

	public void cadastrar(Agencia agencia) {
		AgenciaHttp agenciaHttp = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());
		if (agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(agenciaHttp.getSituacaoCadastral().ATIVO)) {
			agencias.add(agencia);
		} else {
			throw new AgenciaNaoAtivaOuNaoEncontradaException("Agência com situação cadastral inválida");
		}
	}

	public Agencia buscarPorId(Integer id) {
		return agencias.stream().filter(a -> a.getId().equals(id)).findFirst()
				.orElseThrow(() -> new AgenciaNaoAtivaOuNaoEncontradaException("Agência não encontrada"));
	}

	public void deletar(Integer id) {
		Agencia agencia = buscarPorId(id);
		agencias.remove(agencia);
	}

	public void alterar(Agencia agencia) {
		Agencia agenciaExistente = buscarPorId(agencia.getId());
		agencias.remove(agenciaExistente);
		agencias.add(agencia);
	}

}

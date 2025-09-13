package br.com.alura.domain.controller;

import org.jboss.resteasy.reactive.RestResponse;

import br.com.alura.domain.Agencia;
import br.com.alura.service.http.AgenciaService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

@Path("/agencias")
public class AgenciaController {
	private AgenciaService agenciaService;

	AgenciaController(AgenciaService agenciaService) {
		this.agenciaService = agenciaService;
	}

	@POST
	public RestResponse<Void> cadastrar(Agencia agencia) {
		agenciaService.cadastrar(agencia);
		return RestResponse.status(RestResponse.Status.CREATED);
	}

	@GET
	@Path("/{id}")
	public RestResponse<Agencia> buscarPorId(Integer id) {
		Agencia agencia = agenciaService.buscarPorId(id);
		return RestResponse.ok(agencia);
	}

	@DELETE
	@Path("/{id}")
	public RestResponse<Void> deletar(Integer id) {
		agenciaService.deletar(id);
		return RestResponse.noContent();
	}

	@PUT
	public RestResponse<Void> alterar(Agencia agencia) {
		agenciaService.alterar(agencia);
		return RestResponse.noContent();
	}

}

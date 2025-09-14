package br.com.alura.service.http;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.alura.domain.http.AgenciaHttp;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/situacao-cadastral")
// Chamador de api externa na porta 8181
@RegisterRestClient(configKey = "situacao-cadastral-api") // Nome da configuração no application.properties
interface SituacaoCadastralHttpService {
	@GET
	@Path("/{cnpj}")
	AgenciaHttp buscarPorCnpj(String cnpj);
}

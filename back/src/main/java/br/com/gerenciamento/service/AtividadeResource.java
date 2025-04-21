package br.com.gerenciamento.service;

import br.com.gerenciamento.domain.Atividade;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/atividades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AtividadeResource {

    @Inject
    private AtividadeService service;

    @GET
    public Response listarTodas() {
        return Response.ok(service.listarTodas()).build();
    }

    @POST
    public Response criarAtividade(Atividade atividade) {
        service.salvar(atividade);
        return Response.status(Response.Status.CREATED).build();
    }
}
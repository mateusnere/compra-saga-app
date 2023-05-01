package br.com.mateusnere;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("compra-coreografada")
public class CompraCoreografadaResource {

    @Inject
    PedidoService pedidoService;

    private Long id = 0L;

    @GET
    @Path("novo-pedido")
    @Produces(MediaType.APPLICATION_JSON)
    public Response novoPedido() {
        pedidoService.realizaPedido(++id, 30);
        pedidoService.realizaPedido(++id, 20);
        pedidoService.realizaPedido(++id, 40);
        pedidoService.realizaPedido(++id, 15);

        return Response.ok().build();
    }
}

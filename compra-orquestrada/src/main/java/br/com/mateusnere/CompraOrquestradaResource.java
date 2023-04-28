package br.com.mateusnere;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("compra-orquestrada")
public class CompraOrquestradaResource {

    @Inject
    PedidoService pedidoService;

    @Inject
    CreditoService creditoService;

    @GET
    @Path("nova-compra")
    @Produces(MediaType.APPLICATION_JSON)
    public Response novaCompra() {
        Long id = 0L;

        comprar(++id, 30);
        comprar(++id, 31);
        comprar(++id, 32);
        comprar(++id, 10);

        return Response.ok().build();
    }

    private void comprar(Long id, int valor) {
        pedidoService.novoPedido(id);

        try {
            creditoService.realizaDebito(id, valor);
            System.out.println("Pedido " + id + " realizado no valor de " + valor + ". Saldo disponível: " + creditoService.getCreditoTotal());
        } catch (IllegalStateException e) {
            pedidoService.cancelarPedido(id);
            System.err.println("Pedido " + id + " estornado no valor de " + valor);
        }
    }
}

package br.com.mateusnere;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class PedidoService {

    @Inject
    CreditoService creditoService;

    Set<Long> pedidos = new HashSet<>();

    public void realizaPedido(Long pedidoId, Integer valor) {
        pedidos.add(pedidoId);

        try {
            creditoService.realizaDebito(pedidoId, valor);
            System.out.println("Pedido " + pedidoId + " realizado no valor " + valor + ". Saldo disponível: " + creditoService.getCreditoDisponivel());
        } catch (IllegalStateException e) {
            this.cancelaPedido(pedidoId);
            System.err.println(e.getMessage());
        }
    }

    public void cancelaPedido(Long pedidoId) {
        pedidos.remove(pedidoId);
    }
}

package br.com.mateusnere;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class PedidoService {

    private Set<Long> pedidos = new HashSet<>();

    public void novoPedido(Long id) {
        pedidos.add(id);
    }

    public void cancelarPedido(Long id) {
        pedidos.remove(id);
    }
}

package br.com.mateusnere;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CreditoService {

    private int creditoTotal;
    private Map<Long, Integer> pedido_valor = new HashMap<>();

    public CreditoService() {
        this.creditoTotal = 100;
    }

    public void realizaDebito(Long pedidoId, int valor) {
        if(valor > creditoTotal) {
            throw new IllegalStateException("Saldo insuficiente");
        }

        creditoTotal -= valor;
        pedido_valor.put(pedidoId, valor);
    }

    public void realizaEstorno(Long id) {
        creditoTotal += pedido_valor.get(id);
        pedido_valor.remove(id);
    }

    public int getCreditoTotal() {
        return this.creditoTotal;
    }
}

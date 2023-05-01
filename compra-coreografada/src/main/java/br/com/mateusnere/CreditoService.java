package br.com.mateusnere;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CreditoService {

    private Integer creditoDisponivel;
    private Map<Long, Integer> pedido_valor = new HashMap<>();

    public CreditoService() {
        this.creditoDisponivel = 100;
    }

    public void realizaDebito(Long id, Integer valor) {
        if(valor > this.creditoDisponivel) {
            throw new IllegalStateException("Saldo insuficiente! Valor estornado no valor " + valor);
        }

        this.pedido_valor.put(id, valor);
        this.creditoDisponivel -= valor;
    }

    public void realizaEstorno(Long id, Integer valor) {
        this.pedido_valor.remove(id);
        this.creditoDisponivel += valor;
    }

    public Integer getCreditoDisponivel() {
        return this.creditoDisponivel;
    }
}

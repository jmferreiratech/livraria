package br.com.casadocodigo.livraria.modelo;

import java.math.BigDecimal;

public class Dinheiro {
    private Moeda moeda;
    private BigDecimal montante;

    public Dinheiro(Moeda moeda, BigDecimal montante) {
        this.moeda = moeda;
        this.montante = montante;
    }

    protected Dinheiro() {
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public BigDecimal getMontante() {
        return montante;
    }

    @Override
    public String toString() {
        return String.format("Dinheiro(%s %s)", moeda.getSimbolo(), montante);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dinheiro)) return false;

        Dinheiro dinheiro = (Dinheiro) o;

        if (moeda != dinheiro.moeda) return false;
        return montante != null ? montante.equals(dinheiro.montante) : dinheiro.montante == null;
    }

    @Override
    public int hashCode() {
        int result = moeda != null ? moeda.hashCode() : 0;
        result = 31 * result + (montante != null ? montante.hashCode() : 0);
        return result;
    }
}

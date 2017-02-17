package br.com.casadocodigo.livraria.conversor;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.casadocodigo.livraria.modelo.Dinheiro;
import br.com.casadocodigo.livraria.modelo.Moeda;
import com.google.common.base.Strings;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@Convert(Dinheiro.class)
public class DinheiroConverter implements Converter<Dinheiro> {
    @Override
    public Dinheiro convert(String s, Class<? extends Dinheiro> aClass, ResourceBundle resourceBundle) {
        if (Strings.isNullOrEmpty(s)) {
            return null;
        }

        for (Moeda moeda : Moeda.values()) {
            if (s.startsWith(moeda.getSimbolo())) {
                return new Dinheiro(moeda, criaMontante(s, moeda, resourceBundle));
            }
        }
        throw new ConversionError(MessageFormat.format(resourceBundle.getString("dinheiro_invalido"), resourceBundle));
    }

    private BigDecimal criaMontante(String s, Moeda moeda, ResourceBundle resourceBundle) {
        try {
            return new BigDecimal(s.replace(moeda.getSimbolo(), "").replace(',', '.').trim());
        } catch (NumberFormatException e) {
            throw new ConversionError(MessageFormat.format(resourceBundle.getString("dinheiro_invalido"), resourceBundle));
        }
    }
}

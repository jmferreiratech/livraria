package br.com.casadocodigo.livraria.conversor;

import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.casadocodigo.livraria.modelo.Dinheiro;
import br.com.casadocodigo.livraria.modelo.Moeda;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class DinheiroConverterTest {
    @Test
    public void converteUmValorEmReais() throws Exception {
        Converter<Dinheiro> converter = new DinheiroConverter();
        assertThat(converter.convert("R$ 1,00", null, null), is(new Dinheiro(Moeda.REAL, new BigDecimal("1.00"))));
    }

    @Test
    public void converteUmValorEmDolares() throws Exception {
        Converter<Dinheiro> converter = new DinheiroConverter();
        assertThat(converter.convert("US$ 49,95", null, null), is(new Dinheiro(Moeda.DOLAR, new BigDecimal("49.95"))));
    }

    @Test(expected = ConversionError.class)
    public void lancaErroDeConversaoQuandoOValorEhInvalido() throws Exception {
        Converter<Dinheiro> converter = new DinheiroConverter();
        converter.convert("noventa pratas!", null, ResourceBundle.getBundle("messages"));
    }

    @Test(expected = ConversionError.class)
    public void lancaErroDeConversaoQuandoOMontanteEhInvalido() {
        Converter<Dinheiro> converter = new DinheiroConverter();
        converter.convert("R$ mil", null, ResourceBundle.getBundle("messages"));
    }

    @Test
    public void converteStringVaziaEmNull() {
        Converter<Dinheiro> converter = new DinheiroConverter();
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        assertThat(converter.convert("", null, bundle), is(nullValue()));
    }
}

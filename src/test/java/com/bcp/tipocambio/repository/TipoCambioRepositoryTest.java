package com.bcp.tipocambio.repository;

import com.bcp.tipocambio.models.TipoCambio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest(
   properties = {
            "spring.jpa.properties.javax.persistence.validation.mode=none"
    }
)
class TipoCambioRepositoryTest {

    @Autowired
    private TipoCambioRepository underTest;

    @Test
    void itShouldSelectTipoCambioByOrigenAndDestino() {
        // Given
        String monedaOrigen="PER";
        String monedaDestino="USD";

        // When
        Optional<TipoCambio> tipoCambioOptional = underTest.findByOrigenAndDestino(monedaOrigen, monedaDestino);

        // Then
        assertThat(tipoCambioOptional)
                .isPresent()
                .hasValueSatisfying(c ->
                {
                    assertThat(c.getOrigen()).isEqualTo(monedaOrigen);
                    assertThat(c.getDestino()).isEqualTo(monedaDestino);
                });
    }

    @Test
    void itShouldNotSelectTipoCambioWhenOrigenOrDestinoDoNotExists() {
        // Given
        String monedaOrigen="PERU";
        String monedaDestino="USD";
        // When
        Optional<TipoCambio> tipoCambioOptional = underTest.findByOrigenAndDestino(monedaOrigen, monedaDestino);
        // Then
        assertThat(tipoCambioOptional)
                .isNotPresent();

    }

    @Test
    void itShouldSaveTipoCambio() {
        // Given
        TipoCambio tipoCambio=new TipoCambio(1L,"PER","USD",100.00D);
        // When
        underTest.save(tipoCambio);
        // Then
        Optional<TipoCambio> optionalTipoCambio = underTest.findByOrigenAndDestino(tipoCambio.getOrigen(),tipoCambio.getDestino());

        assertThat(optionalTipoCambio)
                .isPresent()
                .hasValueSatisfying(
                        c->{
                            assertThat(c.getId()).isEqualTo(tipoCambio.getId());
                            assertThat(c.getOrigen()).isEqualTo(tipoCambio.getOrigen());
                            assertThat(c.getDestino()).isEqualTo(tipoCambio.getDestino());
                            assertThat(c.getValor()).isEqualTo(tipoCambio.getValor());
                        }
                );
    }


    @Test
    void itShouldReturnAllWhenFindAllIsCalled(){
        //Given

        //When
        List<TipoCambio> todos = underTest.findAll();

        //Then
        assertThat(todos).hasSize(12);
    }



}
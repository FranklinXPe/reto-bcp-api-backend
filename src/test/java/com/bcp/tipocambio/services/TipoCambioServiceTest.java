package com.bcp.tipocambio.services;

import com.bcp.tipocambio.dto.request.CambioMonedaResponse;
import com.bcp.tipocambio.dto.request.TipoCambioRequest;
import com.bcp.tipocambio.exception.TipoCambioNotFoundException;
import com.bcp.tipocambio.models.TipoCambio;
import com.bcp.tipocambio.repository.TipoCambioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

class TipoCambioServiceTest {

    @Mock
    private TipoCambioRepository tipoCambioRepository;

    @Captor
    private ArgumentCaptor<TipoCambio> tipoCambioArgumentCaptor;

    private TipoCambioService underTest;

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.initMocks(this);
        underTest = new TipoCambioService(tipoCambioRepository);

        TipoCambio tipoCambio=new TipoCambio(1L,"USD","PER",3.50);

        Mockito.doReturn(Optional.of(tipoCambio)).when(tipoCambioRepository).findByOrigenAndDestino(anyString(),anyString());
    }

    @Test
    void itShouldReturnMonedaTipoCambio() {
        // Given
        String monedaOrigen="USD";
        String monedaDestino="PER";
        Double monto=1000D;

        // When
        CambioMonedaResponse cambioMonedaResponse = underTest.calcularTipoCambio(monedaOrigen, monedaDestino, monto);

        // Then
       assertThat(monedaOrigen).isEqualTo(cambioMonedaResponse.getOrigen());
       assertThat(monedaDestino).isEqualTo(cambioMonedaResponse.getDestino());
       assertThat(monto).isEqualTo(cambioMonedaResponse.getMonto());
       assertThat(3500.00).isEqualTo(cambioMonedaResponse.getValor());
    }

    @Test
    void itShouldThrowTipoCambioNotFoundExceptionWhenDoesNotFindTipoCambio() {
        // Given
        String monedaOrigen="USD";
        String monedaDestino="PERU";
        Double monto=1000D;

        given(tipoCambioRepository.findByOrigenAndDestino(any(),any())).willReturn(Optional.empty());

        // When
        // Then
       Assertions.assertThatThrownBy(() -> underTest.calcularTipoCambio(monedaOrigen, monedaDestino, monto) )
                        .isInstanceOf(TipoCambioNotFoundException.class)
                        .hasMessageContaining(String.format("No existe el tipo de cambio de %s a %s",monedaOrigen,monedaDestino));
    }


    @Test
    void itShouldUpdateTipoCambio() {
        // Given
        TipoCambioRequest request=new TipoCambioRequest("USD","PER",3.50);

        // When
        underTest.actualizarTipoCambio(request);

        // Then
        then(tipoCambioRepository).should().save(tipoCambioArgumentCaptor.capture());
        TipoCambio tipoCambioArgumentCaptorValue = tipoCambioArgumentCaptor.getValue();

        assertThat(tipoCambioArgumentCaptorValue).isEqualToIgnoringGivenFields(request,"id");
    }

    @Test
    void itShouldThrowWhenTipoCambioNotExist(){
        //Given
        TipoCambioRequest request=new TipoCambioRequest("USD","PERU",0D);
        given(tipoCambioRepository.findByOrigenAndDestino(anyString(),anyString())).willReturn(Optional.empty());

        //When
        assertThatThrownBy(()-> underTest.actualizarTipoCambio(request))
                .isInstanceOf(TipoCambioNotFoundException.class);
    }


}
package com.bcp.tipocambio.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TipoCambioRequest {

    @NotNull
    @NotBlank
    private String origen;
    @NotNull
    @NotBlank
    private String destino;

    @NotNull
    @Min(value = 1)
    private Double valor;

    public TipoCambioRequest() {
    }

    public TipoCambioRequest(@NotNull @NotBlank String origen, @NotNull @NotBlank String destino, @NotNull @Min(value = 1) Double valor) {
        this.origen = origen;
        this.destino = destino;
        this.valor = valor;

    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoCambioRequest)) return false;
        TipoCambioRequest that = (TipoCambioRequest) o;
        return getOrigen().equals(that.getOrigen()) &&
                getDestino().equals(that.getDestino()) &&
                getValor().equals(that.getValor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrigen(), getDestino(), getValor());
    }
}

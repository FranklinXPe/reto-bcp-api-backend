package com.bcp.tipocambio.dto.request;

import com.bcp.tipocambio.models.TipoCambio;

public class CambioMonedaResponse {

    private String origen;
    private String destino;
    private Double monto;
    private Double valor;
    private Double tipoCambio;

    public CambioMonedaResponse() {
    }

    public CambioMonedaResponse(String origen, String destino, Double monto, Double valor, Double tipoCambio) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
        this.valor = valor;
        this.tipoCambio = tipoCambio;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getMonto() {
        return monto;
    }

    public double getValor() {
        return valor;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}

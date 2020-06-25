package com.bcp.tipocambio.dto.response;

public class TipoCambioResponse {
    private String origen;
    private String destino;
    private Double valor;

    public TipoCambioResponse() {
    }

    public TipoCambioResponse(String origen, String destino, double valor) {
        this.origen = origen;
        this.destino = destino;
        this.valor = valor;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

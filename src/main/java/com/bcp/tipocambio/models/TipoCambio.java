package com.bcp.tipocambio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tipocambio")
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class TipoCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
   @Column(nullable = false)
    private String origen;

    @NotBlank
    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private Double valor;

    public TipoCambio() {
    }

    public TipoCambio(Long id, String origen, String destino, Double valor) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TipoCambio.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("origen='" + origen + "'")
                .add("destino='" + destino + "'")
                .add("valor=" + valor)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoCambio)) return false;
        TipoCambio that = (TipoCambio) o;
        return getId().equals(that.getId()) &&
                getOrigen().equals(that.getOrigen()) &&
                getDestino().equals(that.getDestino()) &&
                getValor().equals(that.getValor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrigen(), getDestino(), getValor());
    }
}

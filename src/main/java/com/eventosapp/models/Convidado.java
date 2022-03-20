package com.eventosapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;


@Entity
public class Convidado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
    @NotEmpty
    private String rg;
    @NotEmpty
    private String nomeConvidado;
    @ManyToOne
    private Evento evento;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeConvidado() {
        return nomeConvidado;
    }

    public void setNomeConvidado(String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}

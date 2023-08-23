package com.anunciospromocoes.springboot.anuncios;

import com.anunciospromocoes.springboot.promocoes.Promocao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "anuncios")
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAnuncio;

    @Column(nullable = false, length = 250)
    private String dsAnuncio;

    @Column(nullable = false)
    private Boolean stAnuncio;

    @ManyToOne
    @JoinColumn(name="idPromocao", nullable = false)
    private Promocao promocao;

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getDsAnuncio() {
        return dsAnuncio;
    }

    public void setDsAnuncio(String dsAnuncio) {
        this.dsAnuncio = dsAnuncio;
    }

    public Boolean getStAnuncio() {
        return stAnuncio;
    }

    public void setStAnuncio(Boolean stAnuncio) {
        this.stAnuncio = stAnuncio;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

}

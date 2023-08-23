package com.anunciospromocoes.springboot.promocoes;

import java.util.List;

import com.anunciospromocoes.springboot.anuncios.Anuncio;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "promocao")
public class Promocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPromocao;

    @Column(length = 50)
    private String nmPromocao;

    @Column(length = 100)
    private String dsPromocao;

    @Column(nullable = false)
    private Boolean stPromocao;

    @OneToMany(mappedBy = "promocao", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Anuncio> anuncios;

    public int getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(int idPromocao) {
        this.idPromocao = idPromocao;
    }

    public String getNmPromocao() {
        return nmPromocao;
    }

    public void setNmPromocao(String nmPromocao) {
        this.nmPromocao = nmPromocao;
    }

    public String getDsPromocao() {
        return dsPromocao;
    }

    public void setDsPromocao(String dsPromocao) {
        this.dsPromocao = dsPromocao;
    }

    public Boolean getStPromocao() {
        return stPromocao;
    }

    public void setStPromocao(Boolean stPromocao) {
        this.stPromocao = stPromocao;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

}

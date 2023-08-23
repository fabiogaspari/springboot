package com.anunciospromocoes.springboot.anuncios;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {
    private final AnuncioRepository anuncioRepository;
    
    public AnuncioController(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    @GetMapping
    public String all(Model model) {
        List<Anuncio> anuncios = anuncioRepository.findAll();
        
        model.addAttribute("anuncios", anuncios);

        return "anuncios/index";
    }

    @GetMapping(path = "/{id}")
    public String get(@PathVariable("id") int anuncioId, Model model) {
        Anuncio anuncio = anuncioRepository.findById(anuncioId)
            .orElseThrow(() -> new NoSuchElementException("Anuncio de id " + anuncioId + " não encontrado"));
                    
        model.addAttribute("anuncio", anuncio);

        return "anuncios/read";
    }

    @PostMapping(produces = "application/json")
    public @ResponseBody Anuncio create(@RequestBody @Valid Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    @PutMapping
    public Anuncio update(@RequestBody @Valid Anuncio anuncio) {
        Anuncio findedAnuncio = anuncioRepository.findById(anuncio.getIdAnuncio())
            .orElseThrow(() -> new NoSuchElementException("Anuncio de id " + anuncio.getIdAnuncio() + " não encontrado"));
    
        findedAnuncio.setDsAnuncio(anuncio.getDsAnuncio());
        findedAnuncio.setPromocao(anuncio.getPromocao());
        findedAnuncio.setStAnuncio(anuncio.getStAnuncio());

        return anuncioRepository.save(findedAnuncio);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int idAnuncio) {
        anuncioRepository.deleteById(idAnuncio);
    }
}

package com.anunciospromocoes.springboot.anuncios;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        List<Anuncio> anuncio = anuncioRepository.findAll();

        model.addAttribute("anuncio", anuncio);

        return "anuncio/index";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int promocaoId, Model model) {
        Anuncio anuncio = anuncioRepository.findById(promocaoId)
            .orElseThrow(() -> new NoSuchElementException("Anuncio de id " + promocaoId + " não encontrado"));
        
        model.addAttribute("anuncio", anuncio);

        return "anuncio/read";
    }

    @GetMapping("/cadastrar")
    public String createForm(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        return "anuncio/insert";
    }

    @PostMapping
    public String post(Anuncio anuncio) {
        Optional<Anuncio> findedAnuncio = anuncioRepository.findById(anuncio.getIdAnuncio());
        Anuncio savedAnuncio = null;

        if (  findedAnuncio.isPresent() ) {
            savedAnuncio = findedAnuncio.get();
            savedAnuncio.setDsAnuncio(anuncio.getDsAnuncio());
            savedAnuncio.setStAnuncio(anuncio.getStAnuncio());
            savedAnuncio.setPromocao(anuncio.getPromocao());
            savedAnuncio = anuncioRepository.save(savedAnuncio);
        } else {
            savedAnuncio = anuncioRepository.save(anuncio);
        }
         
        return "redirect:/anuncio/"+savedAnuncio.getIdAnuncio();
    }

    @GetMapping("/atualizar/{id}")
    public String updateForm(@PathVariable("id") int promocaoId, Model model) {
        Anuncio anuncio = anuncioRepository.findById(promocaoId)
            .orElseThrow(() -> new NoSuchElementException("Anuncio de id " + promocaoId + " não encontrado"));;
        
        model.addAttribute("anuncio", anuncio);
        
        return "anuncio/update";
    }

    @PutMapping("/{idPromocao}")
    public String update(@PathVariable("idPromocao") int promocaoId, Anuncio anuncio) {
        Anuncio findedAnuncio = anuncioRepository.findById(anuncio.getIdAnuncio())
            .orElseThrow(() -> new NoSuchElementException("Anuncio de id " + anuncio.getIdAnuncio() + " não encontrado"));
    
        findedAnuncio.setDsAnuncio(anuncio.getDsAnuncio());
        findedAnuncio.setStAnuncio(anuncio.getStAnuncio());
        findedAnuncio.setPromocao(anuncio.getPromocao());

        findedAnuncio = anuncioRepository.save(findedAnuncio);

        return "redirect:/anuncio/"+findedAnuncio.getIdAnuncio();
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody void delete(@PathVariable("id") int idPromocao) {
        anuncioRepository.deleteById(idPromocao);
    }
}

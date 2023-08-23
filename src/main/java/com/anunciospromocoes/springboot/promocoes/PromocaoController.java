package com.anunciospromocoes.springboot.promocoes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
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
@RequestMapping("/promocoes")
public class PromocaoController {
    private final PromocaoRepository promocaoRepository;
    
    public PromocaoController(PromocaoRepository promocaoRepository) {
        this.promocaoRepository = promocaoRepository;
    }

    @GetMapping
    public String all(Model model) {
        List<Promocao> promocoes = promocaoRepository.findAll();

        model.addAttribute("promocoes", promocoes);

        return "promocoes/index";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int promocaoId, Model model) {
        Promocao promocao = promocaoRepository.findById(promocaoId)
            .orElseThrow(() -> new NoSuchElementException("Promoção de id " + promocaoId + " não encontrado"));
        
        model.addAttribute("promocao", promocao);

        return "promocoes/read";
    }

    @GetMapping("/cadastrar")
    public String createForm(Model model) {
        model.addAttribute("promocao", new Promocao());
        return "promocoes/insert";
    }

    @PostMapping
    public String create(@RequestBody @Valid Promocao promocao) {
        Promocao savedPromocao = promocaoRepository.save(promocao);
        System.out.println("entrando");
        return "redirect:/promocoes/"+savedPromocao.getIdPromocao();
    }

    @PutMapping
    public Promocao update(@RequestBody @Valid Promocao promocao) {
        Promocao findedPromocao = promocaoRepository.findById(promocao.getIdPromocao())
            .orElseThrow(() -> new NoSuchElementException("Promoção de id " + promocao.getIdPromocao() + " não encontrado"));
    
        findedPromocao.setDsPromocao(promocao.getDsPromocao());
        findedPromocao.setNmPromocao(promocao.getNmPromocao());
        findedPromocao.setAnuncios(promocao.getAnuncios());
        findedPromocao.setStPromocao(promocao.getStPromocao());

        return promocaoRepository.save(findedPromocao);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody void delete(@PathVariable("id") int idPromocao) {
        promocaoRepository.deleteById(idPromocao);
    }
}

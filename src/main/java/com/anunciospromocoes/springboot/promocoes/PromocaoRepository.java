package com.anunciospromocoes.springboot.promocoes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocaoRepository extends JpaRepository<Promocao, Integer> {
}

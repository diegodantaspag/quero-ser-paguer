package com.javatechie.spring.mysql.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.javatechie.spring.mysql.api.dto.ProdutoDTO;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "preco_sugerido")
	private BigDecimal precoSugerido;
	
	
	public Produto() {
		super();
	}

	public Produto(ProdutoDTO produtoDto) {
		this.nome = produtoDto.getNome();
		this.precoSugerido = produtoDto.getPrecoSugerido();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPrecoSugerido() {
		return precoSugerido;
	}

	public void setPrecoSugerido(BigDecimal precoSugerido) {
		this.precoSugerido = precoSugerido;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", precoSugerido=" + precoSugerido + "]";
	}



	
	
}














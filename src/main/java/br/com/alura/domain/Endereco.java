package br.com.alura.domain;

import jakarta.persistence.*;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

	private String rua;
	private String logradouro;
	private String complemento;
	private Integer numero;

	// Construtor vazio
	public Endereco() {}

	// Getters e setters
	public Integer getId() { return id; }
	public String getRua() { return rua; }
	public String getLogradouro() { return logradouro; }
	public String getComplemento() { return complemento; }
	public Integer getNumero() { return numero; }

	public void setRua(String rua) { this.rua = rua; }
	public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
	public void setComplemento(String complemento) { this.complemento = complemento; }
	public void setNumero(Integer numero) { this.numero = numero; }
}

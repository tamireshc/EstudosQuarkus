package br.com.alura.domain;

import jakarta.persistence.*;

@Entity
public class Agencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

	private String nome;

	@Column(name = "razao_social")
	private String razaoSocial;

	private String cnpj;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	// Construtor vazio
	public Agencia() {}

	// Getters e setters
	public Integer getId() { return id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getRazaoSocial() { return razaoSocial; }
	public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj; }
	public Endereco getEndereco() { return endereco; }
	public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}
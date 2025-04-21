package br.com.gerenciamento.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Atividade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String titulo;
	private LocalDateTime dataInicio;
	
	@ManyToMany
	private List<Pessoa> pessoas;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getTitulo() {return titulo;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	
	public LocalDateTime getDataInicio() {return dataInicio;}
	public void setDataInicio(LocalDateTime dataInicio) {this.dataInicio = dataInicio;}
	
	public List<Pessoa> getPessoa() {return pessoas;}
	public void (List<Pessoa> pessoas) { this.pessoas = pessoas; }
	
}
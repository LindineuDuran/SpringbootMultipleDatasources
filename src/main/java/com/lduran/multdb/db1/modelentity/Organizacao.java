package com.lduran.multdb.db1.modelentity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "Organizacoes")
@Getter
@Setter
@ToString
public class Organizacao extends ObjectBI
{
	@Id
	@Column(name = "IdOrganizacao")
	private String cnpj = "";

	@Column(name = "Organizacao")
	private String razaoSocial;

	@Column(name = "IdCidade")
	private String codigoIBGE = "";

	@Column(name = "UF")
	private String uf = "";
}
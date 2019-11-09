package com.lduran.multdb.db1.modelentity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "Produtos_Servicos")
@Getter
@Setter
@ToString
public class ProdServ extends ObjectBI
{
	@Id
	private String IdProdServ = "";

	private String descricao = "";

	private String tipoProdServ = "";
}
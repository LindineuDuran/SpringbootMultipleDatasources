package com.lduran.multdb.db1.modelentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComercialPK implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(name = "IdChaveNotaFiscal")
	private String chave = "";

	@Column(name = "ItemNFe")
	private Integer itemNfe = 0;

	@Column(name = "Numero")
	private String numero = "";

	@Column(name = "Serie")
	private String serie = "";
}
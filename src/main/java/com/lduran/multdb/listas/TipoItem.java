package com.lduran.multdb.listas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoItem
{
	TI00("Mercadoria para Revenda"),
	TI01("Matéria-prima"),
	TI02("Embalagem"),
	TI03("Produto em Processo"),
	TI04("Produto Acabado"),
	TI05("Subproduto"),
	TI06("Produto Intermediário"),
	TI07("Material de Uso e Consumo"),
	TI08("Ativo Imobilizado"),
	TI09("Serviços"),
	TI10("Outros insumos"),
	TI99("Outras");

	private String descricao;
}
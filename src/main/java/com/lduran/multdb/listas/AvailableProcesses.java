package com.lduran.multdb.listas;

import java.util.*;
import java.util.stream.Stream;

public enum AvailableProcesses
{
	Organizacoes("|0000|"), ProdServ("|0200|"), NotaFiscalDeServico("|A010|", "|A100|", "|A170|"), MovimentacoesComerciais("|C010|", "|C100|", "|C175|");

	private String[] grupo;

	/**
	 * @param grupo
	 */
	private AvailableProcesses(String... grupo)
	{
		this.grupo = grupo;
	}

	/**
	 * @return the grupo
	 */
	public List<String> getGrupo()
	{
		List<String> lstGrupo = new LinkedList<>();

		for (String grp : Arrays.asList(this.grupo))
		{
			lstGrupo.add(grp);
		}

		return lstGrupo;
	}

	public static Stream<AvailableProcesses> stream()
	{
		return Stream.of(AvailableProcesses.values());
	}
}
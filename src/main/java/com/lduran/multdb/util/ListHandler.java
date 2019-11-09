package com.lduran.multdb.util;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lduran.multdb.db1.modelentity.ObjectBI;
import com.lduran.multdb.modelservice.BuildService;

@Component("lst")
public class ListHandler
{
	@Autowired
	BuildService bs;

	/**
	 * Filters data for the desired group in the stream
	 *
	 * @param dir
	 * @param file
	 * @param grupo
	 * @return
	 */
	private List<String> extractGroupInfo(List<String> file, List<String> grupo)
	{
		// Build the RegEx
		StringBuilder ptn = ToolsFactory.getInstance().makePattern(grupo);

		// Receive the pattern
		Pattern p = Pattern.compile(ptn.toString());

		// Filter the desired groups info
		try
		{
			List<String> auxList = file.stream().filter(p.asPredicate()).collect(Collectors.toList());

			return auxList;
		}
		catch (Exception e)
		{
			System.out.println("Error of Stream");

			List<String> auxList = new LinkedList<>();
			return auxList;
		}
	}

	/**
	 * Receive data and return object of type Organization
	 *
	 * @param file
	 * @return
	 */
	public ObjectBI processFileInfo(List<String> file, String objectType, List<String> grupo)
	{
		// Processa informações da Lista
		List<String> auxList = this.extractGroupInfo(file, grupo);

		// Gera ObjectBI
		ObjectBI objectBI = null;
		if (!auxList.isEmpty())
		{
			objectBI = this.bs.getObjectService(objectType).getObject(auxList);
		}

		return objectBI;
	}

	/**
	 * Recebe dados de Escrituração Fiscal Digital e retorna objeto do
	 * tipo Organizacao
	 *
	 * @param file
	 * @return
	 */
	public List<? extends ObjectBI> processFileInfo(List<String> file, String objectType, String criteria, List<String> grupo)
	{
		// Processa informações da Lista
		List<String> auxList = this.extractGroupInfo(file, grupo);

		// Gera List<ObjectBI>
		List<? extends ObjectBI> objectList = new LinkedList<>();
		if (!auxList.isEmpty())
		{
			objectList = this.bs.getObjectService(objectType).getObjectList(auxList, criteria);
		}

		return objectList;
	}
}

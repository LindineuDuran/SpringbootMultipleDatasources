package com.lduran.multdb.process;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lduran.multdb.db1.modelentity.*;
import com.lduran.multdb.listas.AvailableProcesses;
import com.lduran.multdb.util.FileHandler;
import com.lduran.multdb.util.ListHandler;

@Component
public class ExtractionCenter
{
	@Autowired
	FileHandler fh;

	@Autowired
	ListHandler lst;

	@Bean
	public void start()
	{
		// Metric Start
		LocalDateTime dateTimeIni = LocalDateTime.now();
		System.out.println("START " + dateTimeIni);

		// Defines the file to be processed
		String inputFile = "C:\\GitHub\\SpringbootMultipleDatasources\\ExampleData.txt";

		// Get number of lines from file
		int qtdLineFile = this.fh.obtemQuantLinhasArq(inputFile);
		System.out.println("The file has " + qtdLineFile + " lines.");

		// Read file and generate list of contents
		List<String> file = new LinkedList<>();
		try
		{
			file = this.fh.readStream(inputFile);
			System.out.println("Launched itens: " + file.size() + " lines.");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Processes Organization Information
		List<String> grupo = AvailableProcesses.Organizacoes.getGrupo();
		Organizacao org = (Organizacao) this.lst.processFileInfo(file, "Organizacao", grupo);

		// Processes Product and Service information, generating a list
		grupo = AvailableProcesses.ProdServ.getGrupo();
		List<ProdServ> lstProdServ = (List<ProdServ>) this.lst.processFileInfo(file, "ProdServ", "", grupo);

		// Processes Service Invoice information, generating a list
		grupo = AvailableProcesses.NotaFiscalDeServico.getGrupo();
		List<Comercial> lstMovimentacoesComerciais = (List<Comercial>) this.lst.processFileInfo(file, "Comercial", "NotaFiscalDeServico", grupo);

		// End of metric
		LocalDateTime dateTimeFim = LocalDateTime.now();
		System.out.println("START " + dateTimeIni + " - END " + dateTimeFim);
	}
}

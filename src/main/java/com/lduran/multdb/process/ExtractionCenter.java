package com.lduran.multdb.process;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lduran.multdb.db1.modelentity.*;
import com.lduran.multdb.db2.modelentity.ComercialH2Agreg;
import com.lduran.multdb.listas.AvailableProcesses;
import com.lduran.multdb.modelservice.*;
import com.lduran.multdb.util.FileHandler;
import com.lduran.multdb.util.ListHandler;

@Component
public class ExtractionCenter
{
	@Autowired
	FileHandler fh;

	@Autowired
	ListHandler lst;

	@Autowired
	BuildService bs;

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

		// Salva Listagens em arquivos
		boolean salvaRelatorio = true;
		if (salvaRelatorio)
		{
			if (org != null)
			{
				List<Organizacao> lstOrg = new LinkedList<>();
				lstOrg.add(org);
				this.salvaRelatorioProcessado(lstOrg, "Organizacao");
			}

			this.salvaRelatorioProcessado(lstProdServ, "ProdServ");

			this.salvaRelatorioProcessado(lstMovimentacoesComerciais, "Comercial");
		}

		// Salva Dados no Banco
		boolean salvaNoBanco = true;
		if (salvaNoBanco)
		{
			Organizacao orgNova = ((OrganizacaoService) this.bs.getObjectService("Organizacao")).save(org);
			((ComercialService) this.bs.getObjectService("Comercial")).saveAll(lstMovimentacoesComerciais);
			((ProdServService) this.bs.getObjectService("ProdServ")).saveAll(lstProdServ);
		}

		// Clears H2 Database before Aggregation Processing
		((ComercialH2Service) this.bs.getObjectService("ComercialH2")).delete();

		// Aggregate Service Invoice values
		((ComercialH2Service) this.bs.getObjectService("ComercialH2")).saveAll(lstMovimentacoesComerciais);
		List<ComercialH2Agreg> lstMovComAgreg = ((ComercialH2Service) this.bs.getObjectService("ComercialH2")).listAgreg();
		lstMovimentacoesComerciais = lstMovComAgreg.stream().map(com -> ((ComercialService) this.bs.getObjectService("Comercial")).convertComercialH2AgregToComercial(com)).collect(Collectors.toList());

		// Saves aggregated values report
		this.salvaRelatorioProcessado(lstMovimentacoesComerciais, "Comercial_AGREG");

		// Clears H2 Database after Aggregation Processing
		((ComercialH2Service) this.bs.getObjectService("ComercialH2")).delete();

		// Process Business Transaction data and insert into Database H2
		String outputFile = "C:\\GitHub\\SpringbootMultipleDatasources\\Registro de PIS COFINS - C100.csv";
		this.fh.readAndProcessStreamOriginal(inputFile, outputFile, false);

		// Returns Aggregation of Business Movement Data
		lstMovComAgreg = ((ComercialH2Service) this.bs.getObjectService("ComercialH2")).listAgreg();
		lstMovimentacoesComerciais = lstMovComAgreg.stream().map(com -> ((ComercialService) this.bs.getObjectService("Comercial")).convertComercialH2AgregToComercial(com)).collect(Collectors.toList());

		// Saves aggregated values report
		try
		{
			List<String> relatorio = this.lst.formataRelatorio(lstMovimentacoesComerciais, "Comercial");
			this.fh.writeStream("C:\\GitHub\\SpringbootMultipleDatasources\\Registro de PIS COFINS - C100_AGREG.csv", relatorio, false);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clears H2 Database after Aggregation Processing
		((ComercialH2Service) this.bs.getObjectService("ComercialH2")).delete();

		// End of metric
		LocalDateTime dateTimeFim = LocalDateTime.now();
		System.out.println("START " + dateTimeIni + " - END " + dateTimeFim);
	}

	/**
	 * Gera Relatório TXT dos Dados Processados
	 *
	 * @param lstObjetosBI
	 */
	private void salvaRelatorioProcessado(List<? extends ObjectBI> lstObjetosBI, String objectType)
	{
		try
		{
			List<String> relatorio = this.lst.formataRelatorio(lstObjetosBI, objectType.replace("_AGREG", ""));
			this.fh.writeStream("C:\\GitHub\\SpringbootMultipleDatasources\\" + objectType + ".csv", relatorio, false);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

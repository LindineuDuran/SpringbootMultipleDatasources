package com.lduran.multdb.db2.modelrepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lduran.multdb.db2.modelentity.ComercialH2;
import com.lduran.multdb.db2.modelentity.ComercialH2Agreg;

@Repository
public interface ComRepoH2 extends CrudRepository<ComercialH2, Long>
{
	@Query(value = "select ORGANIZACAO, OPERACAO, EMISSAO, PARTICIPANTE, SITUACAO, PRODUTO, DATAEMISSAO, DATAMOVIMENTO, PAGAMENTO, CSTCOFINS, CSTPIS, ALIQUOTACOFINS, ALIQUOTAPIS, BASECREDITO, ORIGEMCREDITO, sum(VALORTOTALNFE) VALORTOTALNFE , sum(VALORTOTAL) VALORTOTAL , sum(DESCONTO) DESCONTO , sum(VALORCOFINS) VALORCOFINS, sum(VALORPIS) VALORPIS, sum(BASECOFINS) BASECOFINS , sum(BASEPIS) BASEPIS  from MOVCOMAGREG group by ORGANIZACAO, OPERACAO, EMISSAO, PARTICIPANTE, SITUACAO, PRODUTO, DATAEMISSAO, DATAMOVIMENTO, PAGAMENTO, CSTCOFINS, CSTPIS, ALIQUOTACOFINS, ALIQUOTAPIS, BASECREDITO, ORIGEMCREDITO order by ORGANIZACAO, DATAEMISSAO", nativeQuery = true)
	public List<ComercialH2Agreg> listAgreg();
}
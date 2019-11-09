package com.lduran.multdb.db1.modelrepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lduran.multdb.db1.modelentity.ProdServ;

@Repository
public interface ProdServRepo extends CrudRepository<ProdServ, String>
{
}
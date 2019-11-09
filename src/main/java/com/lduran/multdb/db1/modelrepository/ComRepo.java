package com.lduran.multdb.db1.modelrepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lduran.multdb.db1.modelentity.Comercial;

@Repository
public interface ComRepo extends CrudRepository<Comercial, Long>
{
}
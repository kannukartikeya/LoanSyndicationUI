package com.chaincode.repositories;

import org.springframework.data.repository.CrudRepository;

import com.chaincode.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}

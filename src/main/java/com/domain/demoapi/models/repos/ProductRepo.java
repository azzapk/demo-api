package com.domain.demoapi.models.repos;

import com.domain.demoapi.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {

}

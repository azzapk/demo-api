package com.domain.demoapi.models.repos;

import com.domain.demoapi.models.entities.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
}

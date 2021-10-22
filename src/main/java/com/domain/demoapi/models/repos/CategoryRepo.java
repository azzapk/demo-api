package com.domain.demoapi.models.repos;

import com.domain.demoapi.models.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}

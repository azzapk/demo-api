package com.domain.demoapi.services;

import com.domain.demoapi.models.entities.Category;
import com.domain.demoapi.models.repos.CategoryRepo;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private EntityManager entityManager;

    public Category save(Category category){
        if (category.getId()!=null){
            Category currentCategory = categoryRepo.findById(category.getId()).get();
            currentCategory.setName(category.getName());
            category = currentCategory;
        }
        return categoryRepo.save(category);
    }

    public Category findOne(Long id){
        Optional<Category> category = categoryRepo.findById(id);
        if (categoryRepo.findById(id).get().isDeleted()){
            return null;
        }
        return category.orElse(null);
    }

    public Iterable<Category> findAll(boolean isDeleted){
//        return categoryRepo.findAll();
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCategoryFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Category> categories = categoryRepo.findAll();
        session.disableFilter("deletedCategoryFilter");
        return categories;
    }

    public void removeOne(Long id){
        categoryRepo.deleteById(id);
    }

    public Iterable<Category> findByName(String name, Pageable pageable, boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCategoryFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Category> categories = categoryRepo.findByNameContains(name, pageable);
        session.disableFilter("deletedCategoryFilter");
        return categories;
    }

    public Iterable<Category> saveBatch(Iterable<Category> categories){
        return categoryRepo.saveAll(categories);
    }
}

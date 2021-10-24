package com.domain.demoapi.services;

import com.domain.demoapi.models.entities.Supplier;
import com.domain.demoapi.models.repos.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id){
        Optional<Supplier> supplier = supplierRepo.findById(id);
        return supplier.orElse(null);
    }

    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }

    public Supplier findByEmail(String email){
        return supplierRepo.findByEmail(email);
    }

    public List<Supplier> findByName(String name){
        return supplierRepo.findByNameContainsOrderByIdDesc(name);
    }

    public List<Supplier> findByNameStartWith(String prefix){
        return supplierRepo.findByNameStartingWith(prefix);
    }

    public List<Supplier> findByNameOrEmail(String name, String email){
        return supplierRepo.findByNameContainsOrEmailContains(name, email);
    }
}

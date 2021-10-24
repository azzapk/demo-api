package com.domain.demoapi.services;

import com.domain.demoapi.models.entities.Product;
import com.domain.demoapi.models.entities.Supplier;
import com.domain.demoapi.models.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public Product findOne(Long id){
        Optional<Product> temp = productRepo.findById(id);
        if (!temp.isPresent()){
            return null;
        }
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findOne(productId);
        if (product == null){
            throw new RuntimeException("Product with ID : "+productId+" not found!");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    public Product findByProductName(String name){
        return productRepo.findByProductName(name);
    }

    public List<Product> findByProductNameLike(String name){
        return productRepo.findByProductByNameLike("%"+name+"%");
    }

    public List<Product> findByCategory(Long categoryId){
        return productRepo.findProductByCategory(categoryId);
    }

    public List<Product> findBySupplier(Long supplierId){
        Supplier supplier = supplierService.findOne(supplierId);
        if (supplier == null){
            return new ArrayList<Product>();
        }
        return productRepo.findProductBySupplier(supplier);
    }
}

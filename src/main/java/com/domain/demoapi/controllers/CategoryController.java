package com.domain.demoapi.controllers;

import com.domain.demoapi.dto.CategoryData;
import com.domain.demoapi.dto.ResponseData;
import com.domain.demoapi.dto.SearchData;
import com.domain.demoapi.models.entities.Category;
import com.domain.demoapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors){

        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return categoryService.findAll(isDeleted);
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id){
        return categoryService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors){

        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        categoryService.removeOne(id);
    }

    @PostMapping("/search/{size}/{page}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size, @PathVariable("page") int page, @RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        Pageable pageable = PageRequest.of(page,size);
        return categoryService.findByName(searchData.getSearchKey(), pageable, isDeleted);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size, @PathVariable("page") int page, @PathVariable("sort") String sort, @RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        if (sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return categoryService.findByName(searchData.getSearchKey(), pageable, isDeleted);
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseData<Iterable<Category>>> createBatch(@RequestBody Category[] categories){

        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        responseData.setPayload(categoryService.saveBatch(Arrays.asList(categories)));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }
}

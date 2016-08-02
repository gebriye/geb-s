package com.geb.repositories;

import org.springframework.data.repository.CrudRepository;

import com.geb.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}

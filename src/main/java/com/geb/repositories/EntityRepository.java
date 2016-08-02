package com.geb.repositories;

import org.springframework.data.repository.CrudRepository;

import com.geb.models.Item;

public interface EntityRepository extends CrudRepository<Item, Integer> {

}

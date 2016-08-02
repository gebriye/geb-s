package com.geb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geb.models.Category;
import com.geb.repositories.CategoryRepository;
import com.geb.response.DataResponse;
import com.geb.response.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "category")
@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	@ApiOperation(value = "Get All Categories", notes = "Returns all categories.")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Response getAllCategories() {
		try {
			return new DataResponse("Success", "Successfully retreived all categorys", categoryRepository.findAll());
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to retreive all categorys", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Get Study", notes = "Get study by ID.")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getCategoryById(@PathVariable("id") int id) {
		Category category;
		try {
			category = categoryRepository.findOne(id);
			if (category != null) {
				return new DataResponse("Success", "Successfully retreived study with id: " + id, category);
			} else {
				return new DataResponse("Error", "Unable to find study with id: " + id, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to find study with id: " + id, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Post new Category", notes = "Create a new category by passing the Category object.")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Response postNewCategory(@RequestBody Category category) {
		try {
			category = categoryRepository.save(category);
			return new DataResponse("Success", "Successfully added the category.", category);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to add category.", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Update and existing Category", notes = "Update and existing category by passing the Category object.")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Response updateCategory(@RequestBody Category category) {
		try {
			// get existing service requests and save them to the object we are
			// saving so they are not overwritten
			//Category oldCategory = categoryRepository.findOne(category.getCategoryId());
			//category.setServiceRequests(oldCategory.getServiceRequests());
			// now save the entire object
			category = categoryRepository.save(category);
			return new DataResponse("Success", "Successfully saved the category.", category);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to save category.", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Delete Category", notes = "Delete category by ID.")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Response deleteCategory(@PathVariable("id") int id) {
		try {
			categoryRepository.delete(id);
			return new DataResponse("Success", "Successfully deleted category.", HttpStatus.OK);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to delete category.", HttpStatus.BAD_REQUEST);
		}
	}
}

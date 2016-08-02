package com.geb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geb.models.SubCategory;
import com.geb.repositories.SubCategoryRepository;
import com.geb.response.DataResponse;
import com.geb.response.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "subcategory")
@RestController
@RequestMapping(value = "/api/subcategory")
public class SubCategoryController {

	@Autowired
	SubCategoryRepository subCategoryRepository;

	@ApiOperation(value = "Get All Categories", notes = "Returns all categories.")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Response getAllCategories() {
		try {
			return new DataResponse("Success", "Successfully retreived all subcategorys", subCategoryRepository.findAll());
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to retreive all subcategorys", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Get Study", notes = "Get study by ID.")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getSubCategoryById(@PathVariable("id") int id) {
		SubCategory subcategory;
		try {
			subcategory = subCategoryRepository.findOne(id);
			if (subcategory != null) {
				return new DataResponse("Success", "Successfully retreived study with id: " + id, subcategory);
			} else {
				return new DataResponse("Error", "Unable to find study with id: " + id, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to find study with id: " + id, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Post new SubCategory", notes = "Create a new subcategory by passing the SubCategory object.")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Response postNewSubCategory(@RequestBody SubCategory subcategory) {
		try {
			subcategory = subCategoryRepository.save(subcategory);
			return new DataResponse("Success", "Successfully added the subcategory.", subcategory);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to add subcategory.", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Update and existing SubCategory", notes = "Update and existing subcategory by passing the SubCategory object.")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Response updateSubCategory(@RequestBody SubCategory subcategory) {
		try {
			// get existing service requests and save them to the object we are
			// saving so they are not overwritten
			//SubCategory oldSubCategory = subcategoryRepository.findOne(subcategory.getSubCategoryId());
			//subcategory.setServiceRequests(oldSubCategory.getServiceRequests());
			// now save the entire object
			subcategory = subCategoryRepository.save(subcategory);
			return new DataResponse("Success", "Successfully saved the subcategory.", subcategory);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to save subcategory.", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Delete SubCategory", notes = "Delete subcategory by ID.")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Response deleteSubCategory(@PathVariable("id") int id) {
		try {
			subCategoryRepository.delete(id);
			return new DataResponse("Success", "Successfully deleted subcategory.", HttpStatus.OK);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to delete subcategory.", HttpStatus.BAD_REQUEST);
		}
	}
}

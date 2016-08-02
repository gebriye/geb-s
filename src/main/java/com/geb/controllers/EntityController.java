package com.geb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geb.models.Item;
import com.geb.repositories.EntityRepository;
import com.geb.response.DataResponse;
import com.geb.response.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "entity")
@RestController
@RequestMapping(value = "/api/entity")
public class EntityController {

	@Autowired
	EntityRepository entityRepository;

	@ApiOperation(value = "Get All Categories", notes = "Returns all categories.")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Response getAllCategories() {
		try {
			return new DataResponse("Success", "Successfully retreived all entitys", entityRepository.findAll());
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to retreive all entitys", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Get Study", notes = "Get study by ID.")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getEntityById(@PathVariable("id") int id) {
		Item entity;
		try {
			entity = entityRepository.findOne(id);
			if (entity != null) {
				return new DataResponse("Success", "Successfully retreived study with id: " + id, entity);
			} else {
				return new DataResponse("Error", "Unable to find study with id: " + id, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to find study with id: " + id, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Post new Entity", notes = "Create a new entity by passing the Entity object.")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Response postNewEntity(@RequestBody Item entity) {
		try {
			entity = entityRepository.save(entity);
			return new DataResponse("Success", "Successfully added the entity.", entity);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to add entity.", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Update and existing Entity", notes = "Update and existing entity by passing the Entity object.")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Response updateEntity(@RequestBody Item entity) {
		try {
			// get existing service requests and save them to the object we are
			// saving so they are not overwritten
			//Entity oldEntity = entityRepository.findOne(entity.getEntityId());
			//entity.setServiceRequests(oldEntity.getServiceRequests());
			// now save the entire object
			entity = entityRepository.save(entity);
			return new DataResponse("Success", "Successfully saved the entity.", entity);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to save entity.", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Delete Entity", notes = "Delete entity by ID.")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Response deleteEntity(@PathVariable("id") int id) {
		try {
			entityRepository.delete(id);
			return new DataResponse("Success", "Successfully deleted entity.", HttpStatus.OK);
		} catch (Exception e) {
			return new DataResponse("Error", "Unable to delete entity.", HttpStatus.BAD_REQUEST);
		}
	}
}

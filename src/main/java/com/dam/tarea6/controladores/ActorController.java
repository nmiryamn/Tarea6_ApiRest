package com.dam.tarea6.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.exception.ActorNotFoundException;
import com.dam.tarea6.servicios.ActorServiceI;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * @author Usuario
 *
 */
@RestController
public class ActorController {

	/**
	 * Objeto de mi servicio de Actor
	 */
	@Autowired
	private ActorServiceI actorServiceI;

	/**
	 * Método que muestra todos los actores.
	 * @return La respuesta de la Api
	 */
	@GetMapping("/mostrarActores")
	public ResponseEntity<?> mostrarActores() {

		// Obtención de actores
		final List<Actor> ActorList = actorServiceI.obtenerTodos();

		if(ActorList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay actores registrados");
		}else {
			return ResponseEntity.ok(ActorList);
		}
	}

	/**
	 * Método que eliminará el actor que tenga el id que le pasamos por parámetro. 
	 * @param id Identificador del Actor
	 * @return Respuesta de la Api
	 */
	@DeleteMapping("/actor/{id}")
	public ResponseEntity<?> eliminarActor(@PathVariable Long id) {

		// Eliminación de actor
		Actor actor = actorServiceI.obtenerActorPorId(id)
				.orElseThrow(()-> new ActorNotFoundException(id));
				actorServiceI.eliminarActor(id);
		return ResponseEntity.noContent().build();

	}

	/**
	 * Método que añade un nuevo actor a la base de datos.
	 * @param newActor Objeto Actor que pasamos por parámetro
	 * @return Respuesta de la Api
	 * @throws Exception
	 */
	@PostMapping("/actor")
	private ResponseEntity<?> aniadirActor(@RequestBody Actor newActor) throws Exception {
			
			Actor a = new Actor();
			
			a.setName(newActor.getName());
			a.setSurname(newActor.getSurname());
			a.setNationality(newActor.getNationality());
			a.setBirthdate(newActor.getBirthdate());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(actorServiceI.anadirActor(a));
	}
	
	/**
	 * Método en el que a partir del id de actor pasado por parámetro actualizamos un actor
	 * @param actorEditado Objeto Actor 
	 * @param id Id del Actor a editar
	 * @return Respuesta de la Api
	 */
	@PutMapping("/actor/{id}")
	public Actor actualizaActor(@RequestBody Actor actorEditado, @PathVariable Long id) {
		
		return actorServiceI.obtenerActorPorId(id).map(p -> {
			p.setName(actorEditado.getName());
			p.setSurname(actorEditado.getSurname());
			p.setBirthdate(actorEditado.getBirthdate());
			p.setNationality(actorEditado.getNationality());
			return actorServiceI.anadirActor(p);
		}).orElseThrow(() -> new ActorNotFoundException(id));
	}
	
	/**
	 * Método que muestra un actor por id 
	 * @param id Identificador del Actor
	 * @return Respuesta de la Api
	 */
	@GetMapping("/actor/{id}")
	public Actor mostrarActor(@PathVariable Long id) {
		
		return actorServiceI.obtenerActorPorId(id).orElseThrow(() -> new ActorNotFoundException(id));
	}
	
}

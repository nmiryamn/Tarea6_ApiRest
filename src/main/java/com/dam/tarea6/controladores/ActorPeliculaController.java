package com.dam.tarea6.controladores;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorModelo;
import com.dam.tarea6.entidades.ActorPelicula;
import com.dam.tarea6.entidades.ActorPeliculaModelo;
import com.dam.tarea6.entidades.Pelicula;
import com.dam.tarea6.entidades.PeliculaModelo;
import com.dam.tarea6.exception.ActorNotFoundException;
import com.dam.tarea6.exception.ActorPeliculaNotFoundException;
import com.dam.tarea6.servicios.ActorPeliculaServiceI;
import com.dam.tarea6.servicios.ActorServiceI;
import com.dam.tarea6.servicios.PeliculaServiceI;

/**
 * 
 * @author Usuario
 *
 */
@RestController
public class ActorPeliculaController {

	/**
	 * Objeto de mi servicio de Actor Película
	 */
	@Autowired
	private ActorPeliculaServiceI actorPeliculaServiceI;

//	/**
//	 * Objeto de mi servicio de Actor 
//	 */
//	@Autowired
//	private ActorServiceI actorServiceI;
//
//	/**
//	 * Objeto de mi servicio de Película 
//	 */
//	@Autowired
//	private PeliculaServiceI peliculaServiceI;


	/**
	 * Muestra todos los objetos ActorPelicula
	 * @return Todos los objetos ActorPelicula de la base de datos
	 */
	@GetMapping("mostarActorPelicula")
	public ResponseEntity<?> mostarActorPeliculas() {
		
		final List<ActorPelicula> actorPeliculaList = actorPeliculaServiceI.obtenerActorPeliculaTodos();
		
		if(actorPeliculaList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay actor-pelicula registrados");
		}else {
			return ResponseEntity.ok(actorPeliculaList);
		}
	}
	
	/**
	 * Obtiene el objeto ActorPelicula que contenga el id pasado por parámetro
	 * @return Muestra el objeto ActorPelicula
	 */
	@GetMapping("/actorpelicula/{id}")
	public ActorPelicula mostrarActorPelicula(@PathVariable Long id) {
		return actorPeliculaServiceI.obtenerActorPeliculaPorId(id).orElseThrow(() -> new ActorPeliculaNotFoundException(id));
	}
	
	/**
	 * Método que crea un objeto ActorPelicula
	 * @param newActorPelicula
	 * @return Añade el objeto a la base de datos
	 */
	@PostMapping("/actorpelicula")
	private ResponseEntity<?> aniadirActorPelicula(@RequestBody ActorPelicula newActorPelicula) {
		return ResponseEntity.status(HttpStatus.CREATED).body(actorPeliculaServiceI.anadirActorPelicula(newActorPelicula));
	}
	
	/**
	 * Actualiza un objeto ActorPelicula a partir del id pasado por parámetro
	 * @param actorPeliculaEditado Objeto ActorPelicula
	 * @param id Identificador del ActorPelicula
	 * @return Actualiza el actor en la base de datos
	 */
	@PutMapping("/actorpelicula/{id}")
	private ActorPelicula actualizarActorPelicula(@RequestBody ActorPelicula actorPeliculaEditado, @PathVariable Long id) {
		return actorPeliculaServiceI.obtenerActorPeliculaPorId(id).map(p -> {
			p.setActor(actorPeliculaEditado.getActor());
			p.setPelicula(actorPeliculaEditado.getPelicula());
			return actorPeliculaServiceI.anadirActorPelicula(p);
		}).orElseThrow(() -> new ActorPeliculaNotFoundException(id));
	}
	
	/**
	 * Método que borra un objeto ActorPelicula que tenga el id pasado por parámetro
	 * @param id Identificador del ActorPelicula
	 * @return Elimina el objeto de la base de datos
	 */
	@DeleteMapping("/actorpelicula/{id}")
	private ResponseEntity<?> eliminarActorPelicula(@PathVariable Long id) {
		// Eliminación de actor-pelicula
				ActorPelicula actorPelicula = actorPeliculaServiceI.obtenerActorPeliculaPorId(id)
						.orElseThrow(()-> new ActorPeliculaNotFoundException(id));
				actorPeliculaServiceI.eliminarActorPeliculaPorId(id);
				return ResponseEntity.noContent().build();

	}
}

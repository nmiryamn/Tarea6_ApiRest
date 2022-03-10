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
import com.dam.tarea6.entidades.Pelicula;
import com.dam.tarea6.entidades.PeliculaModelo;
import com.dam.tarea6.exception.ActorNotFoundException;
import com.dam.tarea6.exception.PeliculaNotFoundException;
import com.dam.tarea6.servicios.PeliculaServiceI;

/**
 * 
 * @author Usuario
 *
 */
@RestController
public class PeliculaController {

	/**
	 * Objeto de mi servicio de películas
	 */
	@Autowired
	private PeliculaServiceI peliculaServiceI;

	/**
	 * Método que muestra todas las películas
	 * @return Todas las películas de la base de datos
	 */
	@GetMapping("/mostrarPeliculas")
	public ResponseEntity<?> mostrarPeliculas() {

		// Obtención de películas
		final List<Pelicula> peliculaList = peliculaServiceI.obtenerTodasPeliculas();

		if(peliculaList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay películas registradas");
		}else {
			return ResponseEntity.ok(peliculaList);
		}
	}

	/**
	 * Método que eliminará la película que tenga el id que pasamos por parámetro.
	 * @param id Identificador del objeto Pelicula
	 * @return 
	 */
	@DeleteMapping("/pelicula/{id}")
	public ResponseEntity<?> eliminarPelicula(@PathVariable Long id) {

		// Eliminación de película
		Pelicula pelicula = peliculaServiceI.obtenerPeliculaPorId(id)
				.orElseThrow(()-> new PeliculaNotFoundException(id));
				peliculaServiceI.eliminarPelicula(id);
		return ResponseEntity.noContent().build();

	}

	/**
	 * Método que añade una nueva película a la base de datos
	 * @param newPelicula Objeto Pelicula
	 * @return Respuesta de la Api
	 * @throws Exception
	 */
	@PostMapping("/pelicula")
	private ResponseEntity<?> aniadirPelicula(@RequestBody Pelicula newPelicula) throws Exception {
		
			Pelicula p = new Pelicula();
			
			p.setTitle(newPelicula.getTitle());
			p.setYear(newPelicula.getYear());
			p.setDuration(newPelicula.getDuration());
			p.setSummary(newPelicula.getSummary());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(peliculaServiceI.anadirPelicula(p));
	}

	/**
	 * Método que actualiza una película
	 * @param peliculaEditada Objeto Pelicula
	 * @param id Identificador de la Pelicula a editar
	 * @return Actualiza la película en la base de datos
	 */
	@PutMapping("/pelicula/{id}")
	public Pelicula actualizaPelicula(@RequestParam Pelicula peliculaEditada, @PathVariable Long id) {
		// Actualización de película
		
		return peliculaServiceI.obtenerPeliculaPorId(id).map(p -> {
			p.setTitle(peliculaEditada.getTitle());
			p.setDuration(peliculaEditada.getDuration());
			p.setYear(peliculaEditada.getYear());
			p.setSummary(peliculaEditada.getSummary());
			return peliculaServiceI.anadirPelicula(p);
		}).orElseThrow(() -> new PeliculaNotFoundException(id));
	}
	
	/**
	 * Método que muestra una película por id
	 * @param id Identificador de la Película a mostrar
	 * @return Muestra la película
	 */
	@GetMapping("/pelicula/{id}")
	public Pelicula mostrarPelicula(@PathVariable Long id) {
		
		return peliculaServiceI.obtenerPeliculaPorId(id).orElseThrow(() -> new PeliculaNotFoundException(id));
	}
}

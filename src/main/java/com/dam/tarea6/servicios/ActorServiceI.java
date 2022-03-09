package com.dam.tarea6.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorModelo;
import com.dam.tarea6.entidades.Pelicula;

public interface ActorServiceI {

	public List<Actor> obtenerTodos();
	
	public Actor anadirActor(final Actor actor);
	
	public void eliminarActor(final Long IdActor);
	
	public void actualizarActor(final Actor actor);
	
	public Optional<Actor> obtenerActorPorId(Long Id);
	
	public Actor obtenerActorPorNombreYAppelidos(String nombre, String apellidos);
	
	public List<Actor> obtenerActoresPorNombre(final String name);
	
	public List<Actor> obtenerActorPorApellidoONacionalidad(final String surname, final String nacionalidad);
	
	public List<Actor> obtenerActorPorApellidoYNacionalidad(final String surname, final String nacionalidad);
}

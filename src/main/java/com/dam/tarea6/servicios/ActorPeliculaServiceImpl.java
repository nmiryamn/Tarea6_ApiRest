package com.dam.tarea6.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorPelicula;
import com.dam.tarea6.entidades.Pelicula;
import com.dam.tarea6.repositorio.ActorPeliculaRepositorio;

/**
 * 
 * @author Usuario
 *
 */
@Service
public class ActorPeliculaServiceImpl implements ActorPeliculaServiceI{
	
	/**
	 * Objeto de mi repositorio de Actor
	 */
	@Autowired
	private ActorPeliculaRepositorio actorPeliculaRepositorio;

	@Override
	public List<ActorPelicula> obtenerActorPeliculaPorIdActor(long idActor) {
		return actorPeliculaRepositorio.findActorPeliculaByActorId(idActor);
	}

	@Override
	public void eliminarActorPeliculaPorId(long id) {
		actorPeliculaRepositorio.deleteById(id);
		
	}

	@Override
	public ActorPelicula anadirActorPelicula(ActorPelicula actorPelicula) {
		return actorPeliculaRepositorio.save(actorPelicula);
	}

	@Override
	public void actualizarActorPelicula(ActorPelicula actorPelicula) {
		actorPeliculaRepositorio.save(actorPelicula);
		
	}

	@Override
	public List<ActorPelicula> obtenerActorPeliculaTodos() {
		return actorPeliculaRepositorio.findAll();
	}

	@Override
	public List<ActorPelicula> obtenerTodos() {
		return actorPeliculaRepositorio.findActorPelicula();
	}

	@Override
	public List<ActorPelicula> obtenerActorPeliculaPorIdPelicula(long idPelicula) {
		return actorPeliculaRepositorio.findActorPeliculaByPeliculaId(idPelicula);
	}

	@Override
	public Optional<ActorPelicula> obtenerActorPeliculaPorId(long id) {
		return actorPeliculaRepositorio.findById(id);
	}

}

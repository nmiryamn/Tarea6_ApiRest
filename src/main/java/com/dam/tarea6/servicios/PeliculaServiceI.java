package com.dam.tarea6.servicios;

import java.util.List;
import java.util.Optional;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.Pelicula;

public interface PeliculaServiceI {

	public List<Pelicula> obtenerTodasPeliculas();
	
	public Pelicula anadirPelicula(final Pelicula pelicula);
	
	public void eliminarPelicula(final long IdPelicula);
	
	public void actualizarPelicula(final Pelicula pelicula);
	
	public Optional<Pelicula> obtenerPeliculaPorId(Long Id);
	
	public Pelicula obtenerPeliculaPorTitulo(String titulo);
	
	public Pelicula obtenerPeliculaPorTituloYAnyo(String title, int anyo);
	
	public List<Pelicula> obtenerPeliculaPorTituloOAnyo(String title, int anyo);
	
	
}

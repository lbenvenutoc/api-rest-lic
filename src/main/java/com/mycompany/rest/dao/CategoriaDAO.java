package com.mycompany.rest.dao;

import java.util.List;

import com.mycompany.rest.domain.Categoria;
import com.mycompany.rest.domain.Cliente;


public interface CategoriaDAO {
	
	public List<Categoria> obtenerCategorias(int codCli);
	public List<Categoria> listarCategorias();
	public void insertarSuscription(Cliente cliente);

}

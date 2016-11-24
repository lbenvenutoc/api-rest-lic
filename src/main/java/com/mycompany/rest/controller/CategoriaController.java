package com.mycompany.rest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mycompany.rest.dao.CategoriaDAO;
import com.mycompany.rest.domain.Categoria;


/**
 * REST service provider
 * 
 * Only GET and POST will return values PUT and DELETE will not.
 */
@Controller
public class CategoriaController {

	@Autowired
	CategoriaDAO categoriaDao;
	
	
	@RequestMapping(value = "/categorias", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Categoria> obtenerListaCategorias() {		
		return categoriaDao.listarCategorias();
	}
	
	
}

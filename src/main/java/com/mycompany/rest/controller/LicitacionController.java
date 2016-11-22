package com.mycompany.rest.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.rest.dao.LicitacionDAO;
import com.mycompany.rest.domain.Licitacion;


/**
 * REST service provider
 * 
 * Only GET and POST will return values PUT and DELETE will not.
 */
@Controller
public class LicitacionController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	LicitacionDAO licitacionDao;
	
	@RequestMapping(value = "/licitaciones/{codCat}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Licitacion> getLicitaciones(@PathVariable("codCat") int codCat) {		
		System.out.println("ENTRA  OBTENER LICITACIONES");
		return licitacionDao.obtenerLicitaciones(codCat);
	}
	
	
}

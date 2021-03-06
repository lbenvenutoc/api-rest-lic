package com.mycompany.rest.controller;

import java.util.ArrayList;
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
import com.mycompany.rest.domain.Notificacion;

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

	@RequestMapping(value = "/licitaciones/{codCat}/{flgAboCli}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Licitacion> obtenerListaLicitaciones(
			@PathVariable("codCat") int codCat,
			@PathVariable("flgAboCli") int flgAboCli) {
		System.out.println("ENTRA  OBTENER LICITACIONES");
		List<Licitacion> lstLicitacion = licitacionDao.obtenerLicitaciones(
				codCat, flgAboCli);
		return lstLicitacion;
	}
	
	@RequestMapping(value = "/licitaciones/porvencer/{codCat}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Notificacion obtenerNumeroLictacionesPorVencer(
			@PathVariable("codCat") int codCat) {
		
		Notificacion notificacion = new Notificacion();
		int numeroNorificacionesPorVencer = licitacionDao
				.obtenerNumeroLictacionesPorVencer(codCat);
		System.out.println("ENTRA  OBTENER obtenerNumeroLictacionesPorVencer "+numeroNorificacionesPorVencer);
		notificacion
				.setNumeroNorificacionesPorVencer(numeroNorificacionesPorVencer);
		return notificacion;
	}
}

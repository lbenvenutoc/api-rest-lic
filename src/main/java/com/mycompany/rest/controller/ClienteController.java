package com.mycompany.rest.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.rest.dao.ClienteDAO;
import com.mycompany.rest.domain.Cliente;

/**
 * REST service provider
 * 
 * Only GET and POST will return values PUT and DELETE will not.
 */
@Controller
public class ClienteController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	ClienteDAO clienteDao;

	@RequestMapping(value = "/clientes/{ruc}/{claUsu}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Cliente obtenerUsuario(@PathVariable("ruc") String ruc,
			@PathVariable("claUsu") String claUsu) {
		System.out.println("ENTRA  OBTENER CLIENTES");
		Cliente cliente = null;
		cliente = clienteDao.obtenerCliente(ruc, claUsu);
		if (cliente == null) {
			cliente = new Cliente();
			cliente.setCodCli(-1);
		}
		return cliente;
	}

	

	@RequestMapping(value = "/clientes/nuevo", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Cliente crearUsuario(@RequestBody Cliente cliente) {
		int resultado = clienteDao.existeCliente(cliente);
		Cliente clienteResultado = null;
		if (resultado == 1) {
			clienteResultado = new Cliente();
			clienteResultado.setCodCli(-2);
			clienteResultado.setRucCli(cliente.getRucCli());
		} else {
			clienteResultado = clienteDao.insertarCliente(cliente);
		}
		return clienteResultado;
	}

}

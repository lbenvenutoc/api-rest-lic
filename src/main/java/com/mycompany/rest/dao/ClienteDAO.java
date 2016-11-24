package com.mycompany.rest.dao;

import com.mycompany.rest.domain.Cliente;

public interface ClienteDAO {
	
	public Cliente insertarCliente(Cliente cliente);
	public Cliente obtenerCliente(String rucCli, String claCli);
	public int existeCliente(Cliente cliente);

}

package com.mycompany.rest.dao;

import java.util.List;
import com.mycompany.rest.domain.Licitacion;

public interface LicitacionDAO {

	public List<Licitacion> obtenerLicitaciones(int codCat, int flgAboCli);
	public int obtenerNumeroLictacionesPorVencer(int codCat);

}

package com.mycompany.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notificacion implements Serializable{
	
	private int numeroNorificacionesPorVencer;

	public int getNumeroNorificacionesPorVencer() {
		return numeroNorificacionesPorVencer;
	}

	public void setNumeroNorificacionesPorVencer(int numeroNorificacionesPorVencer) {
		this.numeroNorificacionesPorVencer = numeroNorificacionesPorVencer;
	}
	
	

}

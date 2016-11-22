package com.mycompany.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Categoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codCat;
	private String desCat;
	private int estSus;
	private boolean estaSeleccionado;
	
	public int getCodCat() {
		return codCat;
	}
	public void setCodCat(int codCat) {
		this.codCat = codCat;
	}
	public String getDesCat() {
		return desCat;
	}
	public void setDesCat(String desCat) {
		this.desCat = desCat;
	}
	public int getEstSus() {
		return estSus;
	}
	public void setEstSus(int estSus) {
		this.estSus = estSus;
	}
	public boolean getEstaSeleccionado() {
		return estaSeleccionado;
	}
	public void setEstaSeleccionado(boolean estaSeleccionado) {
		this.estaSeleccionado = estaSeleccionado;
	}
	
	
	
	
	

}

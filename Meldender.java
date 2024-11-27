package com.tuturial;

import javax.faces.bean.ApplicationScoped;

import jakarta.inject.Named;

@Named
@ApplicationScoped
public class Meldender extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String meldender;



	public String getMeldender() {
		return meldender;
	}

	public void setMeldender(String meldender) {
		this.meldender = meldender;
	}


	private Meldender neuerMeldender = null;
	
	public String add() {
		return "erfolgreich";
	}
	
	public Meldender getNeuerMeldender() {
		if(null == neuerMeldender) {
			this.neuerMeldender = new Meldender();
		}
		
		return this.neuerMeldender;
	}
	
}

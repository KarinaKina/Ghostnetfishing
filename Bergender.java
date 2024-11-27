package com.tuturial;

import javax.faces.bean.SessionScoped;

import jakarta.inject.Named;

@Named
@SessionScoped
public class Bergender extends User {

	private String bergender;


	public String getBergender() {
		return bergender;
	}

	public void setBergender(String bergender) {
		this.bergender = bergender;
	}

	
	
	
	
}

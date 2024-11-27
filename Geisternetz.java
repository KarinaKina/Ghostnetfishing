package com.tuturial;

import java.io.Serializable;
import java.sql.Array;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="ghostnet")
public class Geisternetz implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;


	@Column(name = "name")
	private String netzName;
	
	@Column(name ="groesse")
	private Float groesse;
	
	@Column(name ="koordinaten")
	private Float koordinaten;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "datum")
	@Temporal(TemporalType.DATE)
	private Date aktuellesDatum;
	
	public Geisternetz() {
		// TODO Auto-generated constructor stub
	}
	

	public int getId() {
		return id;
	}


	
	public String getNetzName() {
		return netzName;
	}
	public void setNetzName(String netzName) {
		this.netzName = netzName;
	}

	public Float getGroesse() {
		return groesse;
	}

	public void setGroesse(Float groesse) {
		this.groesse = groesse;
	}

	public Float getKoordinaten() {
		return koordinaten;
	}

	public void setKoordinaten(Float koordinaten) {
		this.koordinaten = koordinaten;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public static void persistenceGeisternetz(Geisternetz neuesGeisternetz) {
		// TODO Auto-generated method stub
		EntityManager em = Persistence.createEntityManagerFactory("ghost").createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
			em.persist(neuesGeisternetz);
		t.commit();
		
		em.close();
	}






	

	
	
	
	
	
}

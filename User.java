package com.tuturial;


import java.io.Serializable;

import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable
{
	
	// artikel
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nickName")
    private String nickName;
	
	@Column(name = "passwort")
    private int passwort;


	  
    public User () {
    	
    }
    
    public User(String nickName, int passwort) {
    	super();
    	this.nickName = nickName;
    	this.passwort = passwort;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getPasswort() {
		return passwort;
	}

	public void setPasswort(int passwort) {
		this.passwort = passwort;
	}
	
	@SuppressWarnings("deprecation")
	public boolean ueberpruefen() {
		try  {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			
	
			Query q = (Query) session.createQuery("FROM user WHERE nickName=:nickName and passwort=:passwort");
			q.setString("nickName", nickName);
			q.setInteger("passwort", passwort);
			
			java.util.List list = q.list();
			
			
			
session.getTransaction().commit();
			
			session.close();
			
		} catch (Exeption e) {
			System.out.print(e);
		}
	}}
	
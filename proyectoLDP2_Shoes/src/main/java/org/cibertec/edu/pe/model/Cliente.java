package org.cibertec.edu.pe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcliente; 
    private String nombrecli; 
    private String emailcli; 
    private String telefonocli; 
    private String direccioncli;
    
    
    //Constructores
    
	public Cliente() {
	}

	public Cliente(int idcliente, String nombrecli, String emailcli, String telefonocli, String direccioncli) {
	
		this.idcliente = idcliente;
		this.nombrecli = nombrecli;
		this.emailcli = emailcli;
		this.telefonocli = telefonocli;
		this.direccioncli = direccioncli;
	}

	//Getters and Setters
	
	
	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombrecli() {
		return nombrecli;
	}

	public void setNombrecli(String nombrecli) {
		this.nombrecli = nombrecli;
	}

	public String getEmailcli() {
		return emailcli;
	}

	public void setEmailcli(String emailcli) {
		this.emailcli = emailcli;
	}

	public String getTelefonocli() {
		return telefonocli;
	}

	public void setTelefonocli(String telefonocli) {
		this.telefonocli = telefonocli;
	}

	public String getDireccioncli() {
		return direccioncli;
	}

	public void setDireccioncli(String direccioncli) {
		this.direccioncli = direccioncli;
	}
     
}

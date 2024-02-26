package org.cibertec.edu.pe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "Proveedor")
	public class Proveedor {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idProveedor; 
	    private String nombreprov; 
	    private String contactoprov; 
	    private String emailpov; 
	    private String telefonoprov; 
	    private String direccionprov;
	    
	    
	    //Constructores
	    
		public Proveedor() {
		}

		public Proveedor(int idProveedor, String nombreprov, String contactoprov, String emailpov, String telefonoprov, String direccionprov) {
		
			this.idProveedor = idProveedor;
			this.nombreprov = nombreprov;
			this.contactoprov = contactoprov;
			this.emailpov = emailpov;
			this.telefonoprov = telefonoprov;
			this.direccionprov = direccionprov;
		}
		//Getters and Setters

		public int getIdProveedor() {
			return idProveedor;
		}

		public void setIdProveedor(int idProveedor) {
			this.idProveedor = idProveedor;
		}

		public String getNombreprov() {
			return nombreprov;
		}

		public void setNombreprov(String nombreprov) {
			this.nombreprov = nombreprov;
		}

		public String getContactoprov() {
			return contactoprov;
		}

		public void setContactoprov(String contactoprov) {
			this.contactoprov = contactoprov;
		}

		public String getEmailpov() {
			return emailpov;
		}

		public void setEmailpov(String emailpov) {
			this.emailpov = emailpov;
		}

		public String getTelefonoprov() {
			return telefonoprov;
		}

		public void setTelefonoprov(String telefonoprov) {
			this.telefonoprov = telefonoprov;
		}

		public String getDireccionprov() {
			return direccionprov;
		}

		public void setDireccionprov(String direccionprov) {
			this.direccionprov = direccionprov;
		}
}

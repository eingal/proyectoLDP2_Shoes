package org.cibertec.edu.pe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
	
	
	@Entity
	@Table(name = "Personal")
	public class Personal {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idPersonal; 
	    private String nombreper; 
	    private String telefonoper; 
	    private String direccionper;
	    
	    
	    //Constructores
	    
		public Personal() {
		}

		public Personal(int idPersonal, String nombreper, String telefonoper, String direccionper) {
		
			this.idPersonal = idPersonal;
			this.nombreper = nombreper;
			this.telefonoper = telefonoper;
			this.direccionper = direccionper;
		}
		//Getters and Setters

		public int getIdPersonal() {
			return idPersonal;
		}

		public void setIdPersonal(int idPersonal) {
			this.idPersonal = idPersonal;
		}

		public String getNombreper() {
			return nombreper;
		}

		public void setNombreper(String nombreper) {
			this.nombreper = nombreper;
		}

		public String getTelefonoper() {
			return telefonoper;
		}

		public void setTelefonoper(String telefonoper) {
			this.telefonoper = telefonoper;
		}

		public String getDireccionper() {
			return direccionper;
		}

		public void setDireccionper(String direccionper) {
			this.direccionper = direccionper;
		}	    
	}


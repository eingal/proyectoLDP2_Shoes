package org.cibertec.edu.pe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "Producto")
	public class Producto {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idProducto; 
	    private String nombreprod; 
	    private String descripcionprod; 
	    private String precioprod; 
	    private String stockprod;
	    
	    
	    //Constructores
	    
		public Producto() {
		}

		public Producto(int idProducto, String nombreprod, String descripcionprod, String precioprod, String stockprod) {
		
			this.idProducto = idProducto;
			this.nombreprod = nombreprod;
			this.descripcionprod = descripcionprod ;
			this.precioprod = precioprod;
			this.stockprod = stockprod;
		}
		//Getters and Setters

		public int getIdProducto() {
			return idProducto;
		}

		public void setIdProducto(int idProducto) {
			this.idProducto = idProducto;
		}

		public String getNombreprod() {
			return nombreprod;
		}

		public void setNombreprod(String nombreprod) {
			this.nombreprod = nombreprod;
		}

		public String getDescripcionprod() {
			return descripcionprod;
		}

		public void setDescripcionprod(String descripcionprod) {
			this.descripcionprod = descripcionprod;
		}

		public String getPrecioprod() {
			return precioprod;
		}

		public void setPrecioprod(String precioprod) {
			this.precioprod = precioprod;
		}

		public String getStockprod() {
			return stockprod;
		}

		public void setStockprod(String stockprod) {
			this.stockprod = stockprod;
		}
}

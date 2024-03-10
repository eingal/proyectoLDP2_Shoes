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
    
   
    private String descripcionprod; 
    private String imagen;
    private double precioprod; 
    private int stockprod;
    
    //Constructores
    
    public Producto() {
    }

	public Producto(int idProducto, String descripcionprod, String imagen, double precioprod, int stockprod) {
		this.idProducto = idProducto;
		this.descripcionprod = descripcionprod;
		this.imagen = imagen;
		this.precioprod = precioprod;
		this.stockprod = stockprod;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcionprod() {
		return descripcionprod;
	}

	public void setDescripcionprod(String descripcionprod) {
		this.descripcionprod = descripcionprod;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public double getPrecioprod() {
		return precioprod;
	}

	public void setPrecioprod(double precioprod) {
		this.precioprod = precioprod;
	}

	public int getStockprod() {
		return stockprod;
	}

	public void setStockprod(int stockprod) {
		this.stockprod = stockprod;
	}
    
    

}
package org.cibertec.edu.pe.model;

import javax.persistence.*;

@Entity
@Table(name = "Detalle")
public class Detalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;

	private int cantidad;
	private double subtotal;

	@ManyToOne
	@JoinColumn(name = "idVentas")
	private Ventas ventas;

	// Constructor, getters y setters

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Ventas getVentas() {
		return ventas;
	}

	public void setVentas(Ventas ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Detalle [idDetalle=" + idDetalle + ", producto=" + producto + ", cantidad=" + cantidad + ", subtotal="
				+ subtotal + "]";
	}
}

package org.cibertec.edu.pe.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVentas;

    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    private double montoTotal;

    @OneToMany(mappedBy = "ventas", cascade = CascadeType.ALL)
    private List<Detalle> detalleVentas;

    // Constructor, getters y setters

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<Detalle> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<Detalle> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    	@Override
	public String toString() {
		return "Venta [idVenta=" + idVentas + ", fechaRegistro=" + fechaVenta + ", montoTotal=" + montoTotal + "]";
	}
	
}
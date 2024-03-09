package org.cibertec.edu.pe.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VentasCab")
public class VentasCab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVentasCab;

    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "ventasCab")
    private List<VentasDet> detallesVenta;


    public int getIdVentasCab() {
        return idVentasCab;
    }

    public void setIdVentasCab(int idVentasCab) {
        this.idVentasCab = idVentasCab;
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

    public List<VentasDet> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<VentasDet> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }
}

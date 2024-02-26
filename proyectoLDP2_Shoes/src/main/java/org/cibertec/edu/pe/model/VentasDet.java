package org.cibertec.edu.pe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

	@Entity
	@Table(name = "VentasDet")
	public class VentasDet {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int idVentasDet;
		@Temporal(TemporalType.DATE)
		private Date fechaRegistro;
		private double montoTotal;
			
		public int getIdVentasDet() {
			return idVentasDet;
		}
		public void setIdVentasDet(int idVentasDet) {
			this.idVentasDet = idVentasDet;
		}
		public Date getFechaRegistro() {
			return fechaRegistro;
		}
		public void setFechaRegistro(Date fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}
		public double getMontoTotal() {
			return montoTotal;
		}
		public void setMontoTotal(double montoTotal) {
			this.montoTotal = montoTotal;
		}
			
		@Override
		public String toString() {
				return "VentasDet [idVentasDet=" + idVentasDet + ", fechaRegistro=" + fechaRegistro + ", montoTotal=" + montoTotal + "]";
		}			
	}

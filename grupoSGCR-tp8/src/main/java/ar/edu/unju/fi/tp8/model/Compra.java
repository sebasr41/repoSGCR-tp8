package ar.edu.unju.fi.tp8.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
	
	@Component
	@Entity
	@Table(name = "COMPRAS")
	public class Compra {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Min(value = 1,message = "La cantidad debe ser mayor o igual a 1 digitos")
		@Max(value = 1000,message = "La cantidad debe ser menor o igual a 1000 digitos")
		@Column(name = "com_cantidad")
		private int cantidad;
		
		
		@Column(name = "com_total")
		private double total;
		
		
		@Autowired
		@ManyToOne
		@JoinColumn(name = "pro_id", nullable = false, updatable = false)
		private Producto producto;

	

	public Compra() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Compra(Long id, int cantidad, double total, Producto producto, List<Producto> productos) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.total = total;
		this.producto = producto;

	}


	/***
	 * 
	public Compra(Long id, int cantidad, double total, Producto producto, List<Producto> productos) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.total = total;
		this.producto = producto;
		this.productos = productos;
	}
	 * 
	 */

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	@Override
	public String toString() {
		return "Compra [id=" + id + ", cantidad=" + cantidad + ", total=" + total + ", producto=" + producto
				 ;
	}
	
	

	/***
	@Override
	public String toString() {
		return "Compra [id=" + id + ", cantidad=" + cantidad + ", total=" + total + ", producto=" + producto
				+ ", productos=" + productos + "]";
	}
	 * 
	 */

	
}

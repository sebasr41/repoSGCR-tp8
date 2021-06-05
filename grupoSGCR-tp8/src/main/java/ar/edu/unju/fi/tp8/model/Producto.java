package ar.edu.unju.fi.tp8.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "PRODUCTOS")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message="Debe tener un Nombre")
	@Size(min=2,max=100,message = "Debe ingresar de 2 a 100 caracteres")
	@Column(name = "prod_nombre", length = 100)
	private String nombre;
	
	@DecimalMin(value = "0.1",message = "El precio debe ser mayor a 0.1")
	@DecimalMax(value = "1000000",message = "El precio debe ser menor a 1000000")
	@Column(name = "prod_precio")
	private double precio;
	
	@NotBlank(message="Debe tener nombre de una Marca")
	@Size(min=2,max=20,message = "Debe ingresar de 2 a 20 caracteres")
	@Column(name = "prod_marca", length = 20)
	private String marca;
	
	@Min(value = 1,message = "El stock debe ser mayor a 1")
	@Max(value = 1000,message = "El stock debe ser menor a 1000")
	@Column(name = "prod_stock")
	private int stock;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Compra> compras;
	
	// Constructor empty
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param nombre
	 * @param precio
	 * @param marca
	 * @param stock
	 * @param compras
	 */
	public Producto(Long id,
			String nombre,
			double precio,
			String marca,
			int stock,
			List<Compra> compras) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.marca = marca;
		this.stock = stock;
		this.compras = compras;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the compras
	 */
	public List<Compra> getCompras() {
		return compras;
	}

	/**
	 * @param compras the compras to set
	 */
	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + ", stock="
				+ stock + ", compras=" + compras + "]";
	}

	
}

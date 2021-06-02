package ar.edu.unju.fi.tp8.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp8.model.Compra;


public interface ICompraService {
	
	public void guardarCompra(Compra compra);
	
	public void generarTablaCompra();
	
	public List<Compra> getAllCompras();

	public Optional<Compra> getCompraPorId(Long id);


	public void eliminarCompra(Long id);
	
	public List<Compra> buscarProductos(String nombre, double total);

}

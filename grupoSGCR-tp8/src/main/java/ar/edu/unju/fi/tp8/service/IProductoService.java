package ar.edu.unju.fi.tp8.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp8.model.Producto;



public interface IProductoService {
	
    public void guardarProducto(Producto producto);

    public void generarTablaProducto();
    
    public List<Producto> obtenerProductos();
    

	public void eliminarProducto(Long id);

	public Optional<Producto> getProductoPorId(Long id);



}

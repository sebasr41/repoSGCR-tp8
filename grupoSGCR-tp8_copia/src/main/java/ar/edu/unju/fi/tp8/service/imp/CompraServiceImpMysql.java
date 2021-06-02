package ar.edu.unju.fi.tp8.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.edu.unju.fi.tp8.model.Compra;
import ar.edu.unju.fi.tp8.repository.ICompraRepository;
import ar.edu.unju.fi.tp8.service.ICompraService;

@Service("compraServiceMysql")
public class CompraServiceImpMysql implements ICompraService {
	
	private static final Log LOGGER = LogFactory.getLog(CompraServiceImpMysql.class);
	
	@Autowired
	private ICompraRepository compraRepository;

	@Override
	public void guardarCompra(Compra compra) {
		// TODO Auto-generated method stub
		double total = compra.getCantidad()*compra.getProducto().getPrecio();
		compra.setTotal(total);
		compraRepository.save(compra);
	}

	@Override
	public void generarTablaCompra() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Compra> getAllCompras() {
		// TODO Auto-generated method stub
		List<Compra> compras= (List<Compra>) compraRepository.findAll();
		return compras;
	}

	@Override
	public Optional<Compra> getCompraPorId(Long id) {
		Optional<Compra> compra = compraRepository.findById(id);
		return compra;
	}


		


	@Override
	public void eliminarCompra(Long id) {
		compraRepository.deleteById(id);
		
	}

	@Override
	public List<Compra> buscarProductos(String nombre, double total) {
		LOGGER.info("anda? :"  + nombre+total);
		List<Compra> compras = new ArrayList<Compra>();
		if(!nombre.isEmpty() && total >=0) {
			compras = compraRepository.findByProductoNombreAndTotalGreaterThanEqual(nombre, total);
		}else if(nombre.isEmpty() && total >=0) {
			compras= compraRepository.findByTotalGreaterThanEqual(total);
		}
		return compras;
	}

}

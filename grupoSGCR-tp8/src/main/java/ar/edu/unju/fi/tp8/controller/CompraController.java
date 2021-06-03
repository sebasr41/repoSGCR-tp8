package ar.edu.unju.fi.tp8.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp8.service.IClienteService;
import ar.edu.unju.fi.tp8.model.Compra;
import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.service.ICompraService;
import ar.edu.unju.fi.tp8.service.IProductoService;



@Controller
public class CompraController {
	@Autowired
	private Compra compra;
	
	private static final Log LOGGER = LogFactory.getLog(CompraController.class);
	
	@Qualifier("productoServiceMysql")
	@Autowired
	private IProductoService productoService;
	
	
	@Qualifier("compraServiceMysql")
	@Autowired
	private ICompraService compraService;
	
	@Qualifier("clienteServiceMysql")
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/compra")
	public String getCompraPage(Model model) {
		model.addAttribute("compra", compra);	
		model.addAttribute("productos", productoService.obtenerProductos());
		if(productoService.obtenerProductos().isEmpty()) {
			model.addAttribute("bandera", false);
		}else {
			model.addAttribute("bandera", true);

		}
		return "nueva-compra";
	}
	@PostMapping("/compra-guardar")
	public ModelAndView getGuardarComprasPage(@Valid @ModelAttribute("compra")Compra compra, BindingResult resultadoValidacion) {
		ModelAndView modelView;
		if(resultadoValidacion.hasErrors()) {
		modelView= new ModelAndView("nueva-compra"); 
		List<Producto> productos = productoService.obtenerProductos();
		modelView.addObject("compra", compra);
		modelView.addObject("productos", productos);
		modelView.addObject("bandera", true);
		return modelView;
		
	
		}
		
		else {
			LOGGER.info("anda? :" + compra);
		ModelAndView model = new ModelAndView("resultado-compra");
		
		Producto producto = productoService.getProductoPorCodigo(compra.getProducto().getCodigo());
		compra.setProducto(producto);
		compraService.guardarCompra(compra);
		model.addObject("compras", compraService.getAllCompras());
		model.addObject("comprab",compra);
		return model;
		}		
		
	}

	@GetMapping("/compra-lista")
	public ModelAndView getComprasPage() {
		ModelAndView model = new ModelAndView("compras");
		if(compraService.getAllCompras() == null) {
			compraService.generarTablaCompra();
		}
		model.addObject("comprab", compra);
		model.addObject("compra", compraService.getAllCompras());
		
		return model;
	
	}
	@GetMapping("/compra-eliminar-{id}")
	public ModelAndView getCompraEliminarPage(@PathVariable (value = "id")Long id) {
		//									
		ModelAndView modelView = new ModelAndView("redirect:/compra-lista");
		compraService.eliminarCompra(id);
		return modelView;
}
	
	@GetMapping("/compra-editar-{id}")
	public ModelAndView getCompraEditPage(@PathVariable (value = "id") Long id) {

		ModelAndView modelView = new ModelAndView("nueva-compra");
		
		Optional<Compra> compra = compraService.getCompraPorId(id);
		
		List<Producto> productos = productoService.obtenerProductos();
		modelView.addObject("compra", compra);
		modelView.addObject("productos", productos);
		
		
		return modelView;
}
	@PostMapping("/compra-busqueda")
	public String buscarCompraPorFiltro(Model model, @ModelAttribute(name="comprab") Compra compra){
		LOGGER.info("anda? :"  + compra);
		
		model.addAttribute("comprab", compra);
	    model.addAttribute("compra", compraService.buscarProductos(compra.getProducto().getNombre(), compra.getTotal()));
	    return "compras";
	}
}


package ar.edu.unju.fi.tp8.controller;

import java.util.Optional;

import javax.validation.Valid;

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

import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.service.IProductoService;



@Controller
public class ProductoController {
	@Autowired
	private Producto producto;
	
	//@Qualifier("productoUtilService")
	@Autowired
	@Qualifier("productoServiceMysql")
	private IProductoService productoService;

	@GetMapping("/producto")
	public String getProductoPage(Model model) {
		model.addAttribute(producto);
		return "nuevo-producto";
	}
	@PostMapping("/producto-guardar")
	public ModelAndView guardarProductoPage(@Valid @ModelAttribute("producto") Producto producto, BindingResult resultadoValidacion){
		
		//
		ModelAndView modelView;
		if(resultadoValidacion.hasErrors()) {
		modelView= new ModelAndView("nuevo-producto"); 
		return modelView;
		}
		
		else {
			 modelView = new ModelAndView("resultado-producto");
		productoService.guardarProducto(producto);
		modelView.addObject("productos", productoService.obtenerProductos());
		
		return modelView;
		}
}
	
	@GetMapping("/producto-ultimo")
	public ModelAndView getProductosPage(){		
		ModelAndView model = new ModelAndView("productos");
		
		if(productoService.obtenerProductos() == null) {
			productoService.generarTablaProducto();
		}
		
		model.addObject("productos", productoService.obtenerProductos());
		
		return model;
	}
	
			@GetMapping("/producto-eliminar-{id}")
			public ModelAndView getProductoEliminarPage(@PathVariable (value = "id")Long id) {
				//									redirect recarga la lista de cuentas
				ModelAndView modelView = new ModelAndView("redirect:/producto-ultimo");
				productoService.eliminarProducto(id);
				return modelView;
}
			
			
			@GetMapping("/producto-editar-{id}")
			public ModelAndView getProductoEditPage(@PathVariable (value = "id") Long id) {
				//ModelAndView modelView = new ModelAndView("nuevo-cliente");
				ModelAndView modelView = new ModelAndView("nuevo-producto");
				Optional<Producto> producto = productoService.getProductoPorId(id);
				modelView.addObject("producto", producto);
				return modelView;
}
}
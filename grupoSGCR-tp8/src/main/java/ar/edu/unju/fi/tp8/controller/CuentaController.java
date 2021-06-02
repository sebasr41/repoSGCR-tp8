package ar.edu.unju.fi.tp8.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp8.model.Cliente;
import ar.edu.unju.fi.tp8.model.Cuenta;
import ar.edu.unju.fi.tp8.service.IClienteService;
import ar.edu.unju.fi.tp8.service.ICuentaService;

@Controller
public class CuentaController {
	
	@Autowired
	private Cuenta cuenta;
	
	//@Qualifier("clienteUtilService")
	@Qualifier("clienteServiceMysql")
	@Autowired
	private IClienteService clienteService;
	
	//@Qualifier("cuentaUtilService")
	@Qualifier("cuentaServiceMysql")
	@Autowired
	private ICuentaService cuentaService;
	
	@GetMapping("/cuenta")
	public String getCuentaPage(Model model) {
		model.addAttribute("cuenta", cuenta);
		model.addAttribute("cliente", clienteService.obtenerClientes());
		return "editar-cuenta";
	}

	@PostMapping("/cuenta-guardar")
	public ModelAndView getGuardarCuentasPage(@Valid @ModelAttribute("cuenta")Cuenta cuenta,  BindingResult resultadoValidacion) {
		ModelAndView modelView;
		if(resultadoValidacion.hasErrors()) {
		modelView= new ModelAndView("editar-cuenta"); 
		return modelView;
		}
		else {
		
		modelView = new ModelAndView("resultado-cuenta");
		
		Cliente cliente = clienteService.getClientePorNroDocumento(cuenta.getCliente().getNroDocumento());
		cuenta.setCliente(cliente);
		cuentaService.guardarCuenta(cuenta);
		modelView.addObject("cliente", cuentaService.getAllCuentas());
		}
		return modelView;
	}
	
	@GetMapping("/cuenta-listado")
	public ModelAndView getCuentasPage() {
		ModelAndView modelView = new ModelAndView("cuentas");
		
		if(cuentaService.getAllCuentas() == null) {
			cuentaService.generarTablaCuenta();
		}
		
		modelView.addObject("cuenta",cuentaService.getAllCuentas());
		return modelView;
	}
	 
}

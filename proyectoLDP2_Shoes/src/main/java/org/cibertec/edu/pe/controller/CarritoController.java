package org.cibertec.edu.pe.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.model.Detalle;
import org.cibertec.edu.pe.model.Producto;
import org.cibertec.edu.pe.model.Ventas;
import org.cibertec.edu.pe.repository.IDetalleRepository;
import org.cibertec.edu.pe.repository.IProductoRepository;
import org.cibertec.edu.pe.repository.IVentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({"carrito", "total"})
public class CarritoController {
	@Autowired
	private IProductoRepository productoRepository;
	@Autowired
	private IVentasRepository ventaRepository;
	@Autowired
	private IDetalleRepository detalleRepository;
	
	@GetMapping("/index")
	public String listado(Model model) {
		List<Producto> lista = new ArrayList<>();
		lista = productoRepository.findAll();
		model.addAttribute("productos", lista);
		return "index";
	}
	
	@GetMapping("/agregar/{idProducto}")
	public String agregar(@PathVariable("idProducto") int idProducto,
            @RequestParam(value = "cantidad", defaultValue = "1") int cantidad,
            HttpSession session) {
	Producto producto = productoRepository.findById(idProducto).orElse(null);
	if (producto != null) {
	List<Detalle> carrito = (List<Detalle>) session.getAttribute("carrito");
	if (carrito == null) {
	  carrito = new ArrayList<>();
	}
	// Verificar si el producto ya está en el carrito
	boolean encontrado = false;
	for (Detalle detalle : carrito) {
	  if (detalle.getProducto().getIdProducto() == idProducto) {
	      detalle.setCantidad(detalle.getCantidad() + cantidad);
	      // Actualizar el subtotal
	      detalle.setSubtotal(detalle.getCantidad() * detalle.getProducto().getPrecioprod());
	      encontrado = true;
	      break;
	  }
	}
	if (!encontrado) {
	  Detalle nuevoDetalle = new Detalle();
	  nuevoDetalle.setProducto(producto);
	  nuevoDetalle.setCantidad(cantidad);
	  // Calcular el subtotal
	  nuevoDetalle.setSubtotal(nuevoDetalle.getCantidad() * producto.getPrecioprod());
	  carrito.add(nuevoDetalle);
	}
	session.setAttribute("carrito", carrito);
	}
	return "redirect:/index";
	}

	@GetMapping("/carrito")
	public String carrito(Model model, HttpSession session) {
	    // Obtener la lista de detalles del carrito de la sesión
	    List<Detalle> carrito = (List<Detalle>) session.getAttribute("carrito");

	    // Verificar si el carrito no es null
	    if (carrito != null) {
	        // Calcular el subtotal
	        double subtotal = calcularSubtotal(carrito);

	        // Obtener y agregar los valores de precioEnvio y descuento al modelo
	        int precioEnvio = calcularPrecioEnvio();
	        int descuento = calcularDescuento();
	        
	        // Calcular el total
	        double total = subtotal + precioEnvio - descuento;

	        // Agregar los valores al modelo
	        model.addAttribute("subtotal", subtotal);
	        model.addAttribute("precioEnvio", precioEnvio);
	        model.addAttribute("descuento", descuento);
	        model.addAttribute("total", total);
	    }

	    return "carrito";
	}

	
	// Inicializacion de variable de la sesion
	@ModelAttribute("carrito")
	public List<Detalle> getCarrito() {
		return new ArrayList<Detalle>();
	}
	
	@ModelAttribute("total")
	public double getTotal() {
		return 0.0;
	}
	
	@GetMapping("/pagar")
	public String pagar(HttpSession session, Model model) {
	    // Obtener el carrito de la sesión
	    List<Detalle> carrito = (List<Detalle>) session.getAttribute("carrito");

	    // Crear una nueva venta y guardarla en la base de datos
	    Ventas venta = new Ventas();
	    venta.setFechaVenta(new Date(System.currentTimeMillis()));
	    venta.setMontoTotal(calcularTotal(carrito));
	    venta = ventaRepository.save(venta);

	    // Guardar los detalles de la venta a la BD shop2023
	    for (Detalle detalle : carrito) {
	        detalle.setVentas(venta);
	        detalleRepository.save(detalle);
	    }

	    // Vaciar el carrito de la sesión
	    session.removeAttribute("carrito");
	    session.removeAttribute("total");
	    // Limpiar el carrito
	    model.addAttribute("carrito", new ArrayList<Detalle>());
	    model.addAttribute("total", 0.0);

	    // Mostrar mensaje
	    return "redirect:/mensaje"; 
	    
	    
	}

	private double calcularTotal(List<Detalle> carrito) {
	    double total = 0.0;
	    for (Detalle detalle : carrito) {
	        total += detalle.getSubtotal();
	    }
	    return total;
	}
	
	@GetMapping("/mensaje")
    public String mostrarMensaje(Model model) {
        model.addAttribute("mensaje", "La compra se ha realizado exitosamente!");
        return "mensaje";
    }

	
	
	// Acción Eliminar
	@GetMapping("/eliminar/{idProducto}")
	public String eliminarDelCarrito(@PathVariable("idProducto") int idProducto, HttpSession session) {
	    List<Detalle> carrito = (List<Detalle>) session.getAttribute("carrito");
	    if (carrito != null) {
	        // Eliminar el producto con idProducto
	        carrito.removeIf(detalle -> detalle.getProducto().getIdProducto() == idProducto);

	        // Recalcular el total del carrito
	        double total = carrito.stream().mapToDouble(Detalle::getSubtotal).sum();
	        
	        session.setAttribute("carrito", carrito);
	        session.setAttribute("total", total);
	    }
	    return "redirect:/carrito";
	}
	
	private double calcularSubtotal(List<Detalle> carrito) {
	    double subtotal = 0.0;
	    for (Detalle detalle : carrito) {
	        subtotal += detalle.getSubtotal();
	    }
	    return subtotal;
	}
	
	// Método para calcular el precio de envío
    private int calcularPrecioEnvio() {
        int precioEnvio = 15;
        return precioEnvio;
    }

    // Método para calcular el descuento del 15%
    private int calcularDescuento() {
        int descuento = 40;
        return descuento;
    }
}


package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.model.Cliente;
import org.cibertec.edu.pe.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "cliente")
public class ClienteController {
    
    @Autowired
    private IClienteRepository clienteRepository;
    
    // Método para mostrar todos los clientes
    @GetMapping(value = "/mostrar")
    public String mostrarCliente(Model model) {
        model.addAttribute("cliente", clienteRepository.findAll());
        return "cliente_list"; // Retorna la vista cliente_list.html
    }
    
    // Método para mostrar formulario de agregar cliente
    @GetMapping(value = "/agregar")
    public String agregarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente_form"; // Retorna la vista cliente_form.html con un cliente vacío
    }
    
    // Método para mostrar formulario de editar cliente
    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("cliente", clienteRepository.findById(id).orElse(null)); // Busca un cliente por ID y lo agrega al modelo
        return "cliente_form"; // Retorna la vista cliente_form.html con los datos del cliente a editar
    }
    
    // Método para eliminar un cliente
    @GetMapping(value = "/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id, RedirectAttributes redirectAttrs) {
        clienteRepository.deleteById(id); // Elimina el cliente por ID
        redirectAttrs
                .addFlashAttribute("mensaje", "Cliente eliminado correctamente") // Agrega un mensaje flash para mostrar en la vista
                .addFlashAttribute("clase", "warning"); // Agrega una clase flash para estilizar el mensaje en la vista
        return "redirect:/cliente/mostrar"; // Redirecciona a la lista de clientes después de la eliminación
    }
    
    // Método para guardar un nuevo cliente
    @PostMapping(value = "/agregar")
    public String guardarCliente(@ModelAttribute @Validated Cliente cliente, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "cliente_form"; // Retorna el formulario con errores si la validación falla
        }
        if (clienteRepository.findFirstByDni(cliente.getDni()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un cliente con ese dni")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/cliente/agregar"; // Redirecciona al formulario de agregar si ya existe un cliente con el mismo DNI
        }
        clienteRepository.save(cliente); // Guarda el nuevo cliente en la base de datos
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/cliente/agregar"; // Redirecciona al formulario de agregar después de agregar correctamente el cliente
    }
    
    // Método para actualizar un cliente existente
    @PostMapping(value = "/editar/{id}")
    public String actualizarCliente(@ModelAttribute @Validated Cliente cliente, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (cliente.getIdcliente() != -1) {
                return "cliente_form"; // Retorna el formulario con errores si la validación falla
            }
            return "redirect:/cliente/mostrar"; // Redirecciona a la lista de clientes si el ID del cliente es inválido
        }       
        
        Cliente posibleClienteExistente = clienteRepository.findFirstByDni(cliente.getDni());

        if (posibleClienteExistente != null && posibleClienteExistente.getIdcliente() != cliente.getIdcliente()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un cliente con ese dni")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/cliente/agregar"; // Redirecciona al formulario de agregar si ya existe un cliente con el mismo DNI
        }
        
        clienteRepository.save(cliente); // Guarda los cambios del cliente en la base de datos
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/cliente/mostrar"; // Redirecciona a la lista de clientes después de editar correctamente el cliente
    }
}

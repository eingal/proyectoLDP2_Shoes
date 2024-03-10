package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.model.Personal;
import org.cibertec.edu.pe.repository.IPersonalRepository;
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
@RequestMapping(path = "personal")
public class PersonalController {
    
    @Autowired
    private IPersonalRepository personalRepository;
    
    // Método para mostrar todo el personal
    @GetMapping(value = "/mostrar")
    public String mostrarPersonal(Model model) {
        model.addAttribute("personal", personalRepository.findAll());
        return "personal_list"; // Retorna la vista personal_list.html
    }
    
    // Método para mostrar formulario de agregar personal
    @GetMapping(value = "/agregar")
    public String agregarPersonal(Model model) {
        model.addAttribute("personal", new Personal());
        return "personal_form"; // Retorna la vista personal_form.html con un personal vacío
    }
    
    // Método para mostrar formulario de editar personal
    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("personal", personalRepository.findById(id).orElse(null)); // Busca un personal por ID y lo agrega al modelo
        return "personal_form"; // Retorna la vista personal_form.html con los datos del personal a editar
    }
    
    // Método para eliminar un personal
    @GetMapping(value = "/eliminar/{id}")
    public String eliminarPersonal(@PathVariable int id, RedirectAttributes redirectAttrs) {
        personalRepository.deleteById(id); // Elimina el personal por ID
        redirectAttrs
                .addFlashAttribute("mensaje", "Personal eliminado correctamente") // Agrega un mensaje flash para mostrar en la vista
                .addFlashAttribute("clase", "warning"); // Agrega una clase flash para estilizar el mensaje en la vista
        return "redirect:/personal/mostrar"; // Redirecciona a la lista de personal después de la eliminación
    }
    
    // Método para guardar nuevo personal
    @PostMapping(value = "/agregar")
    public String guardarPersonal(@ModelAttribute @Validated Personal personal, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "personal_form"; // Retorna el formulario con errores si la validación falla
        }
        if (personalRepository.findFirstByDni(personal.getDni()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un personal con ese dni")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/personal/agregar"; // Redirecciona al formulario de agregar si ya existe un personal con el mismo DNI
        }
        personalRepository.save(personal); // Guarda el nuevo personal en la base de datos
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/personal/agregar"; // Redirecciona al formulario de agregar después de agregar correctamente el personal
    }
    
    // Método para actualizar un personal existente
    @PostMapping(value = "/editar/{id}")
    public String actualizarCliente(@ModelAttribute @Validated Personal personal, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (personal.getIdPersonal() != -1) {
                return "cliente_form"; // Retorna el formulario con errores si la validación falla
            }
            return "redirect:/personal/mostrar"; // Redirecciona a la lista de personal si el ID del personal es inválido
        }       
        
        Personal posiblePersonalExistente = personalRepository.findFirstByDni(personal.getDni());

        if (posiblePersonalExistente != null && posiblePersonalExistente.getIdPersonal() != personal.getIdPersonal()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un personal con ese dni")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/personal/agregar"; // Redirecciona al formulario de agregar si ya existe un personal con el mismo DNI
        }
        
        personalRepository.save(personal); // Guarda los cambios del personal en la base de datos
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/personal/mostrar"; // Redirecciona a la lista de personal después de editar correctamente el personal
    }
}

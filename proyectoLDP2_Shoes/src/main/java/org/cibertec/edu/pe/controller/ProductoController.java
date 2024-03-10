package org.cibertec.edu.pe.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.cibertec.edu.pe.model.Producto;
import org.cibertec.edu.pe.repository.IProductoRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/producto")
public class ProductoController {

    @Autowired
    private IProductoRepository productoRepository;

    // Método para mostrar todos los productos
    @GetMapping("/mostrar")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "producto_list"; // Retorna la vista producto_list.html
    }

    // Método para mostrar formulario de agregar producto
    @GetMapping("/agregar")
    public String agregarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto_form"; // Retorna la vista producto_form.html con un producto vacío
    }

    // Método para mostrar formulario de editar producto
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto == null) {
            // Redirecciona a la lista de productos si el producto no existe
            return "redirect:/producto/mostrar";
        }
        model.addAttribute("producto", producto);
        return "producto_form"; // Retorna la vista producto_form.html con los datos del producto a editar
    }

    // Método para eliminar un producto
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id, RedirectAttributes redirectAttrs) {
        productoRepository.deleteById(id); // Elimina el producto por ID
        redirectAttrs
                .addFlashAttribute("mensaje", "Producto eliminado correctamente") // Agrega un mensaje flash para mostrar en la vista
                .addFlashAttribute("clase", "warning"); // Agrega una clase flash para estilizar el mensaje en la vista
        return "redirect:/producto/mostrar"; // Redirecciona a la lista de productos después de la eliminación
    }

    // Método para guardar un nuevo producto
    @PostMapping("/agregar")
    public String guardarProducto(@Validated @ModelAttribute Producto producto, BindingResult result,
            Model model, @RequestParam("filename") MultipartFile imagen, RedirectAttributes attribute) {
        if (result.hasErrors()) {
            model.addAttribute("producto", producto);

            attribute.addFlashAttribute("mensaje", "Existe un error al agregar");
            attribute.addFlashAttribute("clase", "warning");
        }

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/images");

            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                producto.setImagen(imagen.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productoRepository.save(producto);

        attribute.addFlashAttribute("mensaje", "Producto agregado correctamente");
        attribute.addFlashAttribute("clase", "success");

        return "redirect:/producto/agregar";
    }

    // Método para actualizar un producto existente
    @PostMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable int id, @ModelAttribute @Validated Producto producto,
            BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "producto_form";
        }

        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente == null) {
            // Redirecciona a la lista de productos si el producto no existe
            return "redirect:/producto/mostrar";
        }

        producto.setIdProducto(id); // Asegurar que el ID del producto sea el correcto
        productoRepository.save(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Producto actualizado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/producto/mostrar";
    }
}

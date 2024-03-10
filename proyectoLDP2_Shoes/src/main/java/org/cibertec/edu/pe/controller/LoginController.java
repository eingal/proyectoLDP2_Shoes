package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.model.Usuario;
import org.cibertec.edu.pe.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public String login(Usuario usuario, Model model) {
        Usuario usuarioValido = usuarioRepository.findByUsuarioAndClave(usuario.getUsuario(), usuario.getClave());
        if (usuarioValido == null) {
            model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
            return "login";
        }
        // Redirige a la página de inicio o a donde necesites
        return "redirect:/cliente/mostrar";
    }
}



package com.victorlobato.curso.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// abrir pagina home
	@GetMapping({"/","/home"})
	public String home() {
		return "home";
	}

	// abrir pagina login
	@GetMapping({"/login"})
	public String login() {
		return "login";
	}

	// login invalido
	@GetMapping({"/login-error"})
	public String loginError(Model model) {
		model.addAttribute("alerta", "erro");
		model.addAttribute("titulo","Credenciais inválidas!");
		model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
		model.addAttribute("subtexto", "Acesso permitido apenas para cadastrados já ativados.");

		return "login";
	}

}

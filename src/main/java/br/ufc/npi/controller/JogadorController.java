package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.service.JogadorService;

@Controller
@RequestMapping(path="/jogadores/")
public class JogadorController {

	@Autowired
	JogadorService service;
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("jogadores");
		
		model.addObject("jogadores", service.getTodosJogadores());
		
		return model;
	}
	
	@RequestMapping(path="/salvar", method=RequestMethod.POST)
	public String salvarJogador(@RequestParam String nomeJogador, @RequestParam Integer idade) {
		service.salvarJogador(nomeJogador, idade);
		
		return "redirect:/jogadores/";
	}
}

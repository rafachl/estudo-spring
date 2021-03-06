package br.edu.utfpr.spring.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.utfpr.spring.mvc.model.TipoProduto;
import br.edu.utfpr.spring.mvc.repository.TipoProdutoRepository;

@Controller
@RequestMapping("/tipoProduto")
public class TipoProdutoController {

	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;
	
	@GetMapping("/")
	public String lista(Model model){
		List<TipoProduto> tiposProduto = tipoProdutoRepository.findAll();
		model.addAttribute("tiposProduto",tiposProduto);
		return "/tipoProduto/lista";
	}
	
	@PostMapping("/")
	public String salvar(@Valid TipoProduto tipoProduto, BindingResult erros,
			Model model){
		if(erros.hasErrors()){
			return "/tipoProduto/novo";	
		}
		tipoProdutoRepository.save(tipoProduto);
		return "redirect:/tipoProduto/";
	}
	
	@GetMapping("/novo")
	public String novo(){
		return "/tipoProduto/novo";
	}
	
	@GetMapping("/{codigo}")
	public String visualizar(@PathVariable Long codigo, Model model){
		TipoProduto tipoProduto = tipoProdutoRepository.findOne(codigo);
		model.addAttribute("tipoProduto",tipoProduto);
		return "/tipoProduto/novo";
	}
	
	@GetMapping("/{codigo}/delete")
	public String delete(@PathVariable Long codigo){
		tipoProdutoRepository.delete(codigo);
		return "redirect:/tipoProduto/";
	}
	
	@GetMapping(path="/json")
	@ResponseBody
	public List<TipoProduto> json(){
		return tipoProdutoRepository.findAll();
	}
	
}











package br.com.springboot.CRUDspringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.CRUDspringboot.model.Usuario;
import br.com.springboot.CRUDspringboot.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired  /*injeção de dependencia, ou seja esta injetando todos os recursos da classe jparepository da qual ela se eextende*/
	private UsuarioRepository usuarioRepository;
	
	
	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Curso SpringBoot API" + name + "!";
	}
	
	
	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuarioRepository.save(usuario);
		
		return "Olá Mundo " + nome + "!";
	}
	
	@GetMapping(value = "listatodos" )// esta anotation usa listatodos no navegador
	@ResponseBody /*Retorna os dados para o corpo da resposta*/
	public ResponseEntity<List<Usuario>> listarUsuarios(){ // método para listar
		List<Usuario> usuarios = usuarioRepository.findAll();// Pega todos os usuarios em uma lista
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*retorna um JSON*/
	}

}

package br.com.springboot.CRUDspringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value = "listartodos" )// esta anotation usa listatodos no navegador e é get porque traz valores
	@ResponseBody /*Retorna os dados para o corpo da resposta*/
	public ResponseEntity<List<Usuario>> listarUsuarios(){ // método para listar
		List<Usuario> usuarios = usuarioRepository.findAll();// Pega todos os usuarios em uma lista
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*retorna um JSON*/
	}
	
	
	@PostMapping(value = "salvar") /*mapeia os valores recebidos*/
	@ResponseBody /*Retorna os dados no corpo da resposta*/
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){/* Recebe os dados para salvar*/
		
		Usuario user = usuarioRepository.save(usuario);/* passa os dados da variavel usuario que recebido
		como parâmetro para a variavel user */
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);/*Retorna o status como criado, ou seja salvo*/
		
	}
	
	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long idUser){
		
		usuarioRepository.deleteById(idUser);
		return new ResponseEntity<String>("O Usuario foi deletado com secesso", HttpStatus.OK);
		
	}
	
	@GetMapping(value = "buscaruserid")
	@ResponseBody
	public ResponseEntity<Usuario> buscar(@RequestParam (name = "idUser") Long idUser){
		
		Usuario usuario = usuarioRepository.findById(idUser).get();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		
	}
	
	@PutMapping(value = "atualizar") /*mapeia os valores recebidos*/
	@ResponseBody /*Retorna os dados no corpo da resposta*/
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){/* Recebe os dados para salvar*/
		
		if(usuario.getId()== null) {
			return new ResponseEntity<String>("Não informado o Id do usuário!", HttpStatus.OK);
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);/* passa os dados da variavel usuario que recebido
		como parâmetro para a variavel user */
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);/*Retorna o status como criado, ou seja salvo*/
		
	}
	
	@GetMapping(value = "buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam (name = "nome") String nome){
		
		List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
		
	}
	

}

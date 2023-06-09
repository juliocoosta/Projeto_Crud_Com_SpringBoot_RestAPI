package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import javax.jws.soap.SOAPBinding.Style;

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

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /* Injeção de Dependencia */
	private UsuarioRepository usuarioRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return " Curso Spring Boot API " + name + "!";
    }
    @RequestMapping(value = "/olamundo/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario); /* grava no banco de dados */
    	
    	return " Olá Mundo " + nome ;
    	
    }
    
    @GetMapping(value = "listatodos") /* Método API */
    @ResponseBody /* retorna os dados no corpo da resposta */
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
     List<Usuario> usuarios =	usuarioRepository.findAll(); /* execulta a consulta no banco de dados */   	
     
     return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /* retorna uma lista em JSON */
    }
    
    @PostMapping(value = "salvar")
    @ResponseBody/* descrição da resposta */
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
    	
    Usuario user =	usuarioRepository.save(usuario);
    
    return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    	
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody/* descrição da resposta */
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String> ("Id não foi informado para atualização.", HttpStatus.OK);
    	}
    	
    Usuario user =	usuarioRepository.saveAndFlush(usuario);
    
    return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    	
    }
    
    
    
    
    @DeleteMapping(value = "delete")
    @ResponseBody/* descrição da resposta */
    public ResponseEntity<String> delete(@RequestParam Long iduser) { /* recebe os dados para deletar */
    	
    usuarioRepository.deleteById(iduser);
    
    return new ResponseEntity<String>("Usuário excluido com sucesso", HttpStatus.OK);
    	
    	
    }
    
    @GetMapping(value = "buscaruserid")
    @ResponseBody/* descrição da resposta */
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) { /* recebe os dados para atualizar ou consultar */
    	
    Usuario usuario = usuarioRepository.findById(iduser).get();
    
    return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    	
    }
   
    @GetMapping(value = "buscarPorNome")
    @ResponseBody/* descrição da resposta */
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) { /* recebe os dados para atualizar ou consultar */
    	
    List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    
    return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    
  }
    
}   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


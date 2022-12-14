package br.com.springboot.CRUDspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.CRUDspringboot.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	/* anotation @Query -consulta no bd, select u from(seleção da classe no caso Usuario)
	 * u where(o que do Usuario o nome - comando upper para transpformar tudo em letra maiscula
	 * trim para não considerar espaço e o like para permitir buscar por caracteres, ou seja 
	 * só com parte do nome escrito   */
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%") 
	List<Usuario> buscarPorNome(String name);
		
	

}

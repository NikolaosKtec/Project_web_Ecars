package eaj.br.web_loja.project_web_ecars.repository;
import eaj.br.web_loja.project_web_ecars.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface Usuario_repository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}







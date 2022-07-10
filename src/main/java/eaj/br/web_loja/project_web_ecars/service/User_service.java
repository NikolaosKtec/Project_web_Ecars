package eaj.br.web_loja.project_web_ecars.service;
import eaj.br.web_loja.project_web_ecars.domain.Usuario;
import eaj.br.web_loja.project_web_ecars.repository.Usuario_repository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class User_service implements UserDetailsService{

    private final Usuario_repository repository;

    public  User_service(Usuario_repository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optional = repository.findByUsername(username);

        if(optional.isPresent()) {
            return optional.get();
        }

        throw new UsernameNotFoundException("User not found");
    }

}









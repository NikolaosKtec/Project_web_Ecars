package eaj.br.web_loja.project_web_ecars;

import eaj.br.web_loja.project_web_ecars.domain.Prod_automobilis;
import eaj.br.web_loja.project_web_ecars.domain.Usuario;
import eaj.br.web_loja.project_web_ecars.repository.Prod_automobilis_repository;
import eaj.br.web_loja.project_web_ecars.repository.Usuario_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ProjectWebEcarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectWebEcarsApplication.class, args);
    }

    @Autowired
    private Prod_automobilis_repository prod_automobilis_repository;

    @Autowired
    private Usuario_repository repository;

    @PostConstruct
    public void init(){

        List<Prod_automobilis> prod_automobilis = Stream.of(
                    new Prod_automobilis(0l,"Oz-ultraleggera-matt-bronze-5x112","aro e tala 18x8 | matéria prima: Al Si 7 Mg",15236.45,8,"na",false),
                    new Prod_automobilis(02l,"OZ Ultraleggera Crystal Titanium","desing: 6 Spokes | Duplos matéria prima: Al Si 7 Mg",15236.45,8,"na",false),
                    new Prod_automobilis(03l,"Kit Freios Brembo Stage 5 ","Pinças Brembo de 6 Pistões | VW Golf GTI 1.8T / Audi A3 8L 1.8T",36386.00,1,"na",false),
                    new Prod_automobilis(04l, "TURBO GARRETT GTX3582R GEN II","Indutor: 66 mm | Exdutor: 82 mm",22580.22,3,"na",false)
                ).collect(Collectors.toList());
        prod_automobilis_repository.saveAll(prod_automobilis);

        List<Usuario> users = Stream.of(
                new Usuario(1L, "admin", encoder().encode("admin"), false, false, false, true, true),
                new Usuario(2L, "user1", encoder().encode("user1"), false, false, false, true, false),
                new Usuario(3L, "user2", encoder().encode("user2"), false, false, false, true, false)

        ).collect(Collectors.toList());

        repository.saveAll(users);


    }
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}

package eaj.br.web_loja.project_web_ecars.service;

import eaj.br.web_loja.project_web_ecars.domain.Prod_automobilis;
import eaj.br.web_loja.project_web_ecars.repository.Prod_automobilis_repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Prod_automobilis_service {

    private Prod_automobilis_repository repository;

    public Prod_automobilis_service(Prod_automobilis_repository repository){
        this.repository = repository;
    }

    public Prod_automobilis insert(Prod_automobilis p){
        return repository.save(p);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Prod_automobilis update(Prod_automobilis p){
        return repository.saveAndFlush(p);
    }

    public Prod_automobilis findById(Long id){
        Optional<Prod_automobilis> FilmeOptional = repository.findById(id);
        return FilmeOptional.orElse(null);
    }

    public List<Prod_automobilis> findAll(){
        return repository.findAll();
    }


}

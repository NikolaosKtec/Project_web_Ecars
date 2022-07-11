package eaj.br.web_loja.project_web_ecars.controller;

import eaj.br.web_loja.project_web_ecars.domain.Prod_automobilis;
import eaj.br.web_loja.project_web_ecars.service.Prod_automobilis_service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Product_handler {

    private Prod_automobilis_service service;

    public  Product_handler(Prod_automobilis_service service){
        this.service = service;
    }

    @GetMapping("/admin")
    public String doAdmin(Model model){

        List<Prod_automobilis> produtos_all = service.findAll();
        List<Prod_automobilis> produtos_valid = new ArrayList<>();

        produtos_all.stream().
                filter(filtred -> (filtred.isDeleted() == false)).
                forEach( filtred -> produtos_valid.add(filtred));


        model.addAttribute("produtos", produtos_valid);

        return"admin";
    }

    @GetMapping("/editar/{id}")
    public String doEditar(Model model, @PathVariable Long id){

        Prod_automobilis prod_automobilis = service.findById(id);

        model.addAttribute("produto",prod_automobilis);

        return "editar";
    }


    @GetMapping("/deletar/{id}")
    public String doDelete( @PathVariable Long id/*, Errors errors*/){

        Prod_automobilis prod_automobilis =  service.findById(id);

//        if(errors.hasErrors()){
//            return "/admin";
//        }
        prod_automobilis.setDeleted(true);

        service.update(prod_automobilis);
        return "redirect://admin";
    }

    @PostMapping("/salvar")
    public String doSave(@ModelAttribute @Valid Prod_automobilis p, Errors errors){
//TODO ,mesmo método para salvar e editar...
        if(errors.hasErrors()){
            return "/editar";
        }

        service.update(p);
        return "/admin";
    }
}

package eaj.br.web_loja.project_web_ecars.controller;

import eaj.br.web_loja.project_web_ecars.domain.Prod_automobilis;
import eaj.br.web_loja.project_web_ecars.service.Prod_automobilis_service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Prod_automobilis_controller {

    private Prod_automobilis_service service;

    public  Prod_automobilis_controller(Prod_automobilis_service service){
        this.service = service;
    }

    @GetMapping({"/index","/"})
    public String dohome(Model model){

         List<Prod_automobilis> produtos_all = service.findAll();
         List<Prod_automobilis> produtos_valid = new ArrayList<>();

         produtos_all.stream().
                 filter(filtred -> filtred.isDeleted() == false).
                 forEach( filtred -> produtos_valid.add(filtred));


            model.addAttribute("produtos", produtos_valid);

        return "index";
    }

    @GetMapping("/teste")
    public String bootstr(){
        return "bootstrTT";
    }
}

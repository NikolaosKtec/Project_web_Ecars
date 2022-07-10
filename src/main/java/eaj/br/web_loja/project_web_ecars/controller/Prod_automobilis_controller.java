package eaj.br.web_loja.project_web_ecars.controller;

import eaj.br.web_loja.project_web_ecars.domain.Prod_automobilis;
import eaj.br.web_loja.project_web_ecars.service.Prod_automobilis_service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Prod_automobilis_controller {

    private Prod_automobilis_service service;

    public  Prod_automobilis_controller(Prod_automobilis_service service){
        this.service = service;
    }

    @GetMapping("/index")
    public String dohome(Model model){

         List<Prod_automobilis> produtos = service.findAll();

            model.addAttribute("produtos", produtos);

        return "index";
    }
}

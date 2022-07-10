package eaj.br.web_loja.project_web_ecars.controller;

import eaj.br.web_loja.project_web_ecars.service.User_service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Usuario_controller {

//    User_service service;
//
//    public  Usuario_controller(User_service service){
//        this.service = service;
//    }

    @GetMapping("/login")
    public String login(Model model){

        return "login";
    }


}

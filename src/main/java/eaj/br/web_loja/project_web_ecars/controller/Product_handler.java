package eaj.br.web_loja.project_web_ecars.controller;

import eaj.br.web_loja.project_web_ecars.domain.Prod_automobilis;
import eaj.br.web_loja.project_web_ecars.service.FileStorageService;
import eaj.br.web_loja.project_web_ecars.service.Prod_automobilis_service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Product_handler {

    private Prod_automobilis_service service;
    private FileStorageService fileStorageService;

    public  Product_handler(Prod_automobilis_service service,FileStorageService fileStorageService){
        this.service = service;
        this.fileStorageService = fileStorageService;
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
        return "redirect:/admin";
    }

    @PostMapping("/salvar")
    public String doSave(@ModelAttribute @Valid Prod_automobilis p, Errors errors, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
//TODO ,mesmo método para salvar e editar...
        if(errors.hasErrors()){
            return "/editar";
        }
        //se é produto cadastrado ou se é um novo

        p.setImageUri(file.getOriginalFilename());

        if(service.findById(p.getId()).equals(null)){

            save_of_product(p);
        }else {

            update_of_product(p);
        }

        fileStorageService.save(file);
        return "redirect:/admin";
    }


    private void update_of_product(Prod_automobilis p){

        service.update(p);
    }

    private void save_of_product(Prod_automobilis p){

        service.insert(p);
    }

}

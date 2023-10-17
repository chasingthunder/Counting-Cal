package br.com.fiap.epiccountingcal.countingCal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/countingCal")
public class CountingController {

    @Autowired
    CountingService service;


    @GetMapping
    public String index(Model model){
        model.addAttribute("countingCal", service.findAll());
        return "/countingcal/index";
    }
    
     @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", "Alimento apagado com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Alimento não encontrado");
        }
        return "redirect:/countingCal";
    }

    @GetMapping("new")
    public String form(Counting counting){
        return "countingCal/form";
    }


  @PostMapping
    public String create(@Valid Counting counting, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "countingCal/form";

        service.save(counting);
        redirect.addFlashAttribute("success", "Alimento adicionado com sucesso");
        return "redirect:/countingCal";
    }
}

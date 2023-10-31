package br.com.fiap.epiccountingcal.countingCal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epiccountingcal.user.User;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/countingCal")
public class CountingController {

    @Autowired
    CountingService service;

     @Autowired
     MessageSource messages;


    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("nome"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("countingCal", service.findAll());
        return "/countingCal/index";
    }
    
     @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", getMessage("countingCal.delete.success"));
        }else{
            redirect.addFlashAttribute("error", getMessage("countingCal.notFound"));
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

    private String getMessage(String code){
        return messages.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    @GetMapping("dec/{id}")
    public String decrement(@PathVariable Long id){
        service.decrement(id);
        return "redirect:/countingCal";
    }

    @GetMapping("inc/{id}")
    public String increment(@PathVariable Long id){
        service.increment(id);
        return "redirect:/countingCal";
    }

    @GetMapping("catch/{id}")
    public String catchCounting(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){
        service.catchCounting(id, User.convert(user));
        return "redirect:/countingCal";
    }

}

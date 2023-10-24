package br.com.fiap.epiccountingcal.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String LoginPage(){
        return "auth/login";
    }

      @GetMapping("/logout")
    public String LogoutPage(){
        return "auth/logout";
    }
    
}

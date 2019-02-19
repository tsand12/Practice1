package Controller;

import Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
@Controller
public class UserFormValidationController implements WebMvcConfigurer {
    //@Override
    public void addViewController(ViewControllerRegistry registry){
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(UserForm userForm){
        return "home";
    }

    @RequestMapping("/results")
    public String results(){
        return "results";
    }

    @PostMapping("/")
    public String checkUserInfo(@Valid UserForm userForm, BindingResult bindingResult, User user){
        if (bindingResult.hasErrors()){
            return "home";
        }



        return "redirect:/results";
    }
}

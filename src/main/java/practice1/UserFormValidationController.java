package practice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.validation.Valid;
@Controller
public class UserFormValidationController{
    @Autowired
    private UserRepository userRepository;
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
    public String checkUserInfo(@Valid UserForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "home";
        }

        User newUser = new User(userForm.getFirstName(), userForm.getLastName(), userForm.getAge());
        userRepository.save(newUser);
        return "redirect:/results";
    }
}

package Practice1;

import Practice1.User;
import Practice1.UserForm;
import Practice1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
@Controller
@EnableTransactionManagement
public class UserFormValidationController implements WebMvcConfigurer {
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
    public String checkUserInfo(@Valid UserForm userForm, BindingResult bindingResult, User user){
        if (bindingResult.hasErrors()){
            return "home";
        }

        User newUser = new User(userForm.getFirstName(), userForm.getLastName(), userForm.getAge());
        userRepository.save(newUser);
        return "redirect:/results";
    }
}

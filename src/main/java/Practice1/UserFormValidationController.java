package practice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.validation.Valid;

@Controller
public class UserFormValidationController {
    @Autowired
    private UserRepository userRepository;


    //@Override
    public void addViewController(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(UserForm userForm) {
        return "home";

    }

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "results";

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(Model model, User user){
        User newUser = new User();
        newUser.setAge(user.getAge());
        newUser.setLastName(user.getLastName());
        newUser.setFirstName(user.getFirstName());

        return newUser;
    }

    @PostMapping("/")
    public String checkUserInfo(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home";
        }

        User newUser = new User(userForm.getFirstName(), userForm.getLastName(), userForm.getAge());
        userRepository.save(newUser);
        return "redirect:/results";
    }
}

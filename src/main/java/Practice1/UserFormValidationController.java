package practice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers(Model model, UserForm userForm){
        //User newUser = new User(userForm.getFirstName(), userForm.getLastName(), userForm.getAge());
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public Optional<User> getUser(@PathVariable("id") long id){

        return userRepository.findById(id);
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

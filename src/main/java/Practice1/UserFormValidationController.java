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



    public void addViewController(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    /**
     *
     * @param userForm class that user form is based off of (including validations)
     * Method to display home page/user form with / uri
     * @return home view
     */
    @GetMapping("/")
    public String showForm(UserForm userForm) {
        return "home";

    }

    /**
     *
     * @param model
     * Method to add all users to model so that it can be displayed in the results page via thymeleaf
     * @return results view that displays all users including newly added user
     */
    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "results";

    }

    /**
     *
     * @param model
     * @param userForm class that user form is based off of (including validations)
     * Method to return json/response with all users
     * @return pretty print json with all users
     */
    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers(Model model, UserForm userForm){
        //User newUser = new User(userForm.getFirstName(), userForm.getLastName(), userForm.getAge());
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    /**
     *
     * @param id id passed in uri to get specific user
     * Method to return json/response of specific user based on id
     * @return pretty print json containing user with matching id
     */
    @GetMapping("/user/{id}")
    @ResponseBody
    public Optional<User> getUser(@PathVariable("id") long id){

        return userRepository.findById(id);
    }

    /**
     *
     * @param userForm class that user form is based off of (including validations)
     * @param bindingResult
     * Method to display User form in home page, add new user upon valid submission of form, and redirect to results
     * page where all users, including newly added user, are displayed
     * @return results view with table of all users
     */
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

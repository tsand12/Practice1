import Repository.UserRepository;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home(){
        return "Hello World";
    }

    public static void main(String[] args){SpringApplication.run(Example.class, args);}

    //@Bean
    public CommandLineRunner runner(UserRepository repository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.err.println(repository.findAll());

            }
        };
    }

}

package practice1;

import org.junit.Test;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;






import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserFormValidationControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void addViewController() {
    }

    @Test
    public void getAllUsers() throws Exception{
        this.mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void showForm() throws Exception{
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

    }


    @Test
    public void results() throws  Exception{

        this.mockMvc.perform(get("/results"))
                .andExpect(status().isOk())
                .andExpect(view().name("results"));
    }

    @Test
    public void checkUserInfo() throws Exception {

        User newUser = new User("Bob", "Smarley",108);
        userRepository.save(newUser);

        this.mockMvc.perform(get("/user/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        assertThat(jsonPath("$.firstName").value(newUser.getFirstName()));
        assertThat(jsonPath("$.lastName").value(newUser.getLastName()));
        assertThat(jsonPath("$.age").value(newUser.getAge()));




    }
}
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



//https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/


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


    /**
     * Test case to ensure all users are returned with the /users uri
     * @throws Exception
     */
    @Test
    public void getAllUsers() throws Exception{
        this.mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test case to ensure home view displays with the / uri
     * @throws Exception
     */
    @Test
    public void showForm() throws Exception{
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

    }

    /**
     * Test case to ensure results view displays with the /results uri
     * @throws Exception
     */
    @Test
    public void results() throws  Exception{

        this.mockMvc.perform(get("/results"))
                .andExpect(status().isOk())
                .andExpect(view().name("results"));
    }

    /**
     *  Test case to check if user was added
     * @throws Exception
     */
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

    /**
     * User form validation test case
     * User input is invalid when input for "age" parameter is less than 18
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_TooYoung() throws Exception {

        MockHttpServletRequestBuilder createUser = post("/")
                .param("firstName", "Ralph")
                .param("lastName", "Wreckit")
                .param("age", "2");

        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is invalid when input for "firstName" is missing
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_MissingFirstName() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/")
                .param("lastName", "Wreckit")
                .param("age", "22");

        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is invalid when input for "firstName" is too short
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_FirstNameTooShort() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/")
                .param("firstName", "R")
                .param("lastName", "Wreckit")
                .param("age", "22");

        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is invalid when input for "lastName" is too short
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_LastNameTooShort() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/")
                .param("firstName", "Ralph")
                .param("lastName", "W")
                .param("age", "22");

        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is invalid when input for "lastName" is too long
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_LastNameTooLong() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/")
                .param("firstName", "Ralph")
                .param("lastName", "WreckitRalphIThoughtTheMovieWasPrettyOkButIAmAlsoEasilyEntertainedSoDontListenToMe")
                .param("age", "22");

        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is invalid when input for "firstName" is too long
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_FirstNameTooLong() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/")
                .param("firstName", "RalphIsABigGuyWhoIsGonnaWreckItButJustCuzHesABadGuyDoesntMeanHesABadGuy")
                .param("lastName", "Wreckit")
                .param("age", "22");

        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is invalid when input for "lastName" is missing
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_MissingLastName() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/")
                .param("firstName", "Ralph")
                .param("age", "22");

        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is invalid when input for "age" is missing
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_MissingAge() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/")
                .param("firstName", "Ralph")
                .param("lastName", "Wreckit");


        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is invalid when input for all fields are missing
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_NoUserInput() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/");

        mockMvc.perform(createUser)
                .andExpect(model().hasErrors());
    }

    /**
     * User form validation test case
     * User input is valid when all fields have entries and all entries follow validations defined in UserForm class
     * @throws Exception
     */
    @Test
    public void validateUserFormInput_ValidForm() throws Exception {
        MockHttpServletRequestBuilder createUser = post("/")
                .param("firstName", "Ralph")
                .param("lastName", "Wreckit")
                .param("age", "22");

        mockMvc.perform(createUser)
                .andExpect(model().hasNoErrors());
    }
}
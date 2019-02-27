package test;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import practice1.UserFormValidationController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserFormValidationController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserFormIntegrationTest{

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

   public void testResultsRetrieval() throws JSONException {

       HttpEntity<String> entity = new HttpEntity<String>(null, headers);

       ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/results"), HttpMethod.GET, entity, String.class);
       String expected = "{id:1, firstName: Charles, lastName:Xavier, age:65}";

       JSONAssert.assertEquals(expected, response.getBody(), false);
   }

   private String createURLWithPort(String uri){
       return "http:localhost:" + port + uri;
   }
}
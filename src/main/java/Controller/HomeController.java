package Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Controller
public class HomeController implements Controller {

    /*public HomeController(){
        super();
    }*/
    /*@RequestMapping(value="/home", method= RequestMethod.GET)
    String home(){
        return "home";
    }*/


    public void process(
            final HttpServletRequest request, final HttpServletResponse response, final ServletContext servletContext,
            final ITemplateEngine templateEngine)throws Exception{

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        templateEngine.process("home", ctx, response.getWriter());
    }
}

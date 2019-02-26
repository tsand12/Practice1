package Practice1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController  {

    /*public HomeController(){
        super();
    }*/
    @RequestMapping("/")
    String home(){
        return "home";
    }


   /* public void process(
            final HttpServletRequest request, final HttpServletResponse response, final ServletContext servletContext,
            final ITemplateEngine templateEngine)throws Exception{

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        templateEngine.process("home", ctx, response.getWriter());
    }*/
}

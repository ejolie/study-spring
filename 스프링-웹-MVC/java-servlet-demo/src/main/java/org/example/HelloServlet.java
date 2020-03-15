package main.java.org.example;

import org.example.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import sun.jvm.hotspot.HelloWorld;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        HelloService helloService = context.getBean(HelloService.class);

        System.out.println("doGet");
        resp.getWriter().print("<html>");
        resp.getWriter().print("<head></head>");
        resp.getWriter().print("<body>");
        resp.getWriter().print("<h1>Hello, " + helloService.getName() + " Servlet</h1>");
        resp.getWriter().print("</body>");
        resp.getWriter().print("</html>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }
}

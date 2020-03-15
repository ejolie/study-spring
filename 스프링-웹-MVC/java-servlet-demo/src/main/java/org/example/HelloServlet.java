package main.java.org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.getWriter().print("<html>");
        resp.getWriter().print("<head></head>");
        resp.getWriter().print("<body>");
        resp.getWriter().print("<h1>Hello Servlet</h1>");
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

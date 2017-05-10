package main.java;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name="HelloServletName", urlPatterns = { "/hello" })
public class HelloServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("hello.jsp");
    request.setAttribute("attribute1", "attribute1 value");
    requestDispatcher.forward(request, response);
  }
}

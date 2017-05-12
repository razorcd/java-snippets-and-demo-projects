package main.java;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

@WebServlet(name="LoginServletName", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {
    Writer resWriter = response.getWriter();
    resWriter.write("received params: " + request.getParameter("userName") + " and " + request.getParameter("password"));
    resWriter.write("<br/>");

    //set Session
    resWriter.write("<p>Setting username to Session.</p>");
    HttpSession session = request.getSession();
    session.setAttribute("username", request.getParameter("userName"));

    resWriter.write("<a href='session'>Show what is in Session</a>");
    response.setContentType("text/html");
  }
}



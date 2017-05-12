package main.java;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

@WebServlet(name="SessionServletName", urlPatterns = { "/session" })
public class SessionServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain");
    Writer resWriter = response.getWriter();
    resWriter.write("Writing session attributes. By default stored in cookie under JSESSIONID key.\n");

    //set Session
    HttpSession session = request.getSession();
    Enumeration<String> attributes = session.getAttributeNames();
    while (attributes.hasMoreElements()) {
      String attr = attributes.nextElement();
      resWriter.write(attr + " = " + session.getAttribute(attr) + "\n");
    }
  }
}



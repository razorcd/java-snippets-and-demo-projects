package com.simpleservlet.demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//@WebServlet(name="simpleservlet", urlPatterns = { "/hello" })
public class HelloServlet extends HttpServlet {

        static final long serialVersionUID = 42L;

  //@Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h1>Hello from Servlet</h2>");
  }
}

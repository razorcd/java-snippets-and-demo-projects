package com.simpleservlet.demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//@WebServlet(name="genservlet", urlPatterns = { "/generic" })
public class GenServlet extends GenericServlet {

  //@Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    System.out.println("Generic servlet called !!!");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h1>Generic Servlet Called</h2>");
  }

}

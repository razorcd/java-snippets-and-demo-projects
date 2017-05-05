package com.simpleservlet.demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//@WebServlet(name="simpleservletAsync", urlPatterns = { "/helloAsync" })
public class HelloServletAsync extends HttpServlet {

   static final long serialVersionUID = 44L;

  //@Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h3>Starting Thread name : " + Thread.currentThread().getName() + "</h3>");
    out.println("<h1>Hello from Servlet</h2>");
    out.println("<h3>isAsyncSupported(): " + request.isAsyncSupported() + "</h3>");
   
    AsyncContext acontext = request.startAsync();

    AsyncContext aContext = request.getAsyncContext();
    aContext.start(() -> {
      out.println("<h3>Starting Thread name : " + Thread.currentThread().getName() + "</h3>");
      int i=0;
      while(i<=10) {
        out.println("<h4>"+ i++ + "</h4>");
        out.flush();  //sends current part of response out without closing connection
        try {Thread.sleep(1000);}
        catch (InterruptedException e) {}
      }
      out.println("<h3>Finishing thead name : " + Thread.currentThread().getName() + "</h3>");
      aContext.complete();
    });

    try {Thread.sleep(1000);}
    catch (InterruptedException e) {}

    out.println("<h1>Done.</h1>");
    out.println("<h3>Finishing thead name : " + Thread.currentThread().getName() + "</h3>");

  }
}

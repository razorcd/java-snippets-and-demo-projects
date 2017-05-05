package com.simpleservlet.demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//@WebFilter(filterName="simpleservletfilter", urlPatterns = { "/helloThroughFilter" })
public class HelloFilter implements Filter {

  private FilterConfig filterConfig;

  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    System.out.println("init methid called in " + this.getClass().getName());
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
   System.out.println("doFilter method called in " + this.getClass().getName()); 
   HttpServletRequest httpRequest = (HttpServletRequest) request;

   response.setContentType("text/html");    
   PrintWriter out = response.getWriter();
   out.println("<h3>You were blocked by servlet filter.</h3>");
    //chain.doFilter(request, response);
  }

  public void destroy() {
    System.out.println("destroy method called in " + this.getClass().getName());

  }
}

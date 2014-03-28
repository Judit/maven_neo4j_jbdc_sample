package com.example;

import com.jdbc.DAO;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.ResultSet;

public class App extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter pw = res.getWriter();

    DAO dao = new DAO();
    ResultSet rs = dao.getFirstNode();

    pw.println("<html><head>");
    pw.println("<title>GrapheneDB on CloudBees example</title>");
    pw.println("</head><body>");
    pw.println("<p>The first node of your graph</p>");
    pw.println("<p>" + rs.toString() + "</p>");
    pw.println("</body>");
    pw.close();
  }
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    doGet(req,res);
  }
}

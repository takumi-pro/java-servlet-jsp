package com.webapp;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/formservlet")
public class FormServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");

      String name = request.getParameter("name");

      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset=\"UTF-8\">");
      out.println("</head>");
      out.println("<body>");
      out.println("<p>あなたの名前は" + name + "ですね？</p>");
      out.println("</body></html>");
  }
}

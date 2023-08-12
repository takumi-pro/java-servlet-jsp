package com.webapp;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import model.User;

@WebServlet("/userform")
public class UserForm extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userForm.jsp");
    dispatcher.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String ageString = request.getParameter("age");
    int age = Integer.parseInt(ageString);

    // モデルUserに値を設定
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setAge(age);

    // リクエストスコープに保存
    request.setAttribute("user", user);

    // forward
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userProfile.jsp");
    dispatcher.forward(request, response);
  }
}

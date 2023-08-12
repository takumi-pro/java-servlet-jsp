package com.webapp;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/dbaccess")
public class DbAccess extends HttpServlet {
  // TODO 環境変数で指定
  static final String DB_URL = "jdbc:postgresql://172.31.0.3:5432/todo";
  static final String DB_USER = "takumi";
  static final String DB_PASS = "takumi";

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("dbasccess");
        PrintWriter pw = response.getWriter();
        try {
          Class.forName("org.postgresql.Driver");
          try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "SELECT id,name,email,age FROM users";
            Statement pStm = conn.createStatement();
            ResultSet result = pStm.executeQuery(sql);
            pw.println(("<html>"));
            pw.println(("<body>"));

            while (result.next()) {
              int id = result.getInt("id");
              String name = result.getString("name");
              String email = result.getString("email");
              int age = result.getInt("age");
              pw.println(("<li>"));
              pw.println(("<span>id: " + id + "</span> "));
              pw.println(("<span>name: " + name + "</span> "));
              pw.println(("<span>email: " + email + "</span> "));
              pw.println(("<span>age: " + age + "</span> "));
              pw.println(("</li>"));
            }

            pw.println(("</body>"));
            pw.println(("</html>"));
          } catch (Exception e) {
            pw.println("error: " + e.getMessage());
            pw.println(("DB接続失敗や！"));
        }
      } catch (Exception e) {
        pw.println(("ドライバーが読み込まれてへん！"));
      }
  }
}

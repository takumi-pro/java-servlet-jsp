<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
User user = (User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <h1>ユーザ情報</h1>
  <p>名前：<%= user.getName() %></p>
  <p>メールアドレス：<%= user.getEmail() %></p>
  <p>年齢：<%= user.getAge() %></p>
  <a href="userform">戻る</a>
</body>
</html>
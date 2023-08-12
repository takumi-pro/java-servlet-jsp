<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <h1>情報入力フォーム</h1>
  <form action="userform" method="POST">
      <label for="name">名前:</label>
      <input type="text" id="name" name="name" required><br><br>

      <label for="email">メールアドレス:</label>
      <input type="email" id="email" name="email" required><br><br>

      <label for="age">年齢:</label>
      <input type="number" id="age" name="age" required><br><br>

      <input type="submit" value="送信">
  </form>
</body>
</html>
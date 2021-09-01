<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ユーザー登録</h1>
<form action="" method="post">
  <p>名前:
  <input type="text" name="name" required>
  </p>
  <p>メール:
  <input type="email" name="email" required>
  </p>
  <p>パスワード:
  <input type="password" name="pass" required>
  </p>
  <p>年齢:
  <input type="number" name="age" min="0" max="150" step="1">
  </p>
  <p><input type="submit"></p>
</form>
</body>
</html>
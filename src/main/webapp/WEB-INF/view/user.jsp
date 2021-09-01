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
<p><a href="userAdd">追加</a></p>
<h1>ユーザ一覧</h1>
<table border="1">
  <tr>
    <th>ID</th>
    <th>名前</th>
    <th>メール</th>
    <th>パスワード</th>
    <th>年齢</th>
  </tr>
  <c:forEach items="${list}" var="user">
  <tr>
    <td><c:out value="${user.id}" /></td>
    <td><c:out value="${user.name}" /></td>
    <td><c:out value="${user.email}" /></td>
    <td><c:out value="${user.pass}" /></td>
    <td><c:out value="${user.age}" /></td>
  </tr>
  </c:forEach>
</table>
</body>
</html>
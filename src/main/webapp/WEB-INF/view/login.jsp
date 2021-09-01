<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>ログイン</h1>
<div class="a">
 <c:if test="${!empty error}">
	<p><c:out value="${error}" /></p>
 </c:if>
</div>

<form action="" method="post">
	<p>メールアドレス:
	<input type="email" name="email">
	</p>
	<p>パスワード:
	<input type="password" name="pass">
	</p>
	<input type="submit" value="ログイン">
</form>
</body>
</html>

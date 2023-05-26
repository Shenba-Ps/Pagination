<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/insert" method="get">

username : <input type="text" name="username">
email : <input type="text" name="email">

<button type="submit">add</button>
<button type="submit" formaction="<%=request.getContextPath()%>/list">view</button>
</form>
</body>
</html>
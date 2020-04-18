<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>
    Sorted array
  </title>
</head>

<body>
  <a href="/">HOME</a><br>
  Sorted array:
  <c:forEach var="element" items="${array}">
    ${element}
  </c:forEach>
</body>
</html>

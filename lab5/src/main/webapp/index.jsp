<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>
    Lab 5
  </title>
</head>
<body>
  <form name="arrayForm" method="GET" action="/sort">
      Enter array: <input type="text" name="array" placeholder="array"/>
      <input type="submit" value="Submit"/>
  </form>
  <c:if test="${error!=null}">
      <br>${error}
  </c:if>
</body>
</html>

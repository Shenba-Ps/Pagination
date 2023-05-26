    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<table>
		<thead>
					<tr>
					<th>SI.NO</th>
						 
						<th>User Name</th>
						<th>Email</th>
						<th>Actions</th>
					</tr>
				</thead>
		<tbody>
		
		            <c:forEach var="user" items="${listUser}" varStatus="counter"> 
		            
		            <tr>
		                   <td> ${counter.count} </td>
		                   
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.email}" /></td>
						
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
		           		            
		            </tr>
		            
		            </c:forEach>
		
		
		
		</tbody>
		</table>
		</div>	
		 <%--For displaying Previous link except for the 1st page --%>
    <%--For displaying Previous link except for the 1st page --%>
  <c:if test="${currentPage != 1}">
      <td><a href="user?page=${currentPage - 1}">Previous</a></td>
  </c:if>
  
  <%--For displaying Page numbers. The when condition does not display
              a link for the current page--%>
  
  <table border="1" cellpadding="5" cellspacing="5">
      <tr>
          <c:forEach begin="1" end="${noOfPages}" var="i">
              <c:choose>
                  <c:when test="${currentPage eq i}">
                      <td>${i}</td>
                  </c:when>
                  <c:otherwise>
                      <td><a href="user?page=${i}">${i}</a></td>
                  </c:otherwise>
              </c:choose>
          </c:forEach>
      </tr>
  </table>
  
  <%--For displaying Next link --%>
  
  <c:if test="${currentPage lt noOfPages}">
      <td><a href="user?page=${currentPage + 1}">Next</a></td>
  </c:if>
		
</body>
</html>
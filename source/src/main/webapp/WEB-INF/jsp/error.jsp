<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
  <div class="error">
   <!-- <p class="messege"><c:out value="${errorMessege}" /><p> -->
     <p class="messege">エラーが発生しました。</p>
  <br>
    <p class="messege">お手数ですがTOPページから
        <br>再度やり直しをお願いします</p>
    </div>

	<c:if test="${not empty errorMessage}">
		<span class="account-error-msg">${errorMessage}</span>
	</c:if>
    <a href="<c:url value='/TopPageServlet' />"> TOP画面へ戻る</a>
</body>
</html>

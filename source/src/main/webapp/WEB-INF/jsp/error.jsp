<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<div class="main-wrapper error-main-wrapper">
  <div class="error-title">
   <!-- <p class="messege"><c:out value="${errorMessege}" /><p> -->
    <p>エラーが発生しました。</p>
  	<br>
    <p class="messege">
    	お手数ですがTOPページから再度やり直しをお願いします。<br>
        以下、エラー内容です。
    </p>
    <br>
    </div>

	<c:if test="${not empty errorMessage}">
		<span class="account-error-msg error-detail-text">${errorMessage}</span>
	</c:if>
	<br>
    <a href="<c:url value='/TopPageServlet' />" class="light-orange-btn to-top-btn"> TOPへ戻る</a>
</div>
</body>
</html>

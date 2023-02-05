
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% HttpSession maSession = request.getSession(); %>

<c:if test="${ !empty sessionScope.message  }">
    <div class="alert alert-${ sessionScope.typeMessage }" style="font-size:16px;">${ sessionScope.message }</div>
    <% maSession.setAttribute( "message" , null ); %>
</c:if>
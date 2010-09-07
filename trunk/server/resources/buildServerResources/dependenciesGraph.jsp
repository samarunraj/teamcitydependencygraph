<%@ include file="/include.jsp" %>

<c:set var="title" value="Test page" scope="request"/>

<jsp:useBean id="name" type="java.lang.String" scope="request"/>
<jsp:useBean id="projectId" type="java.lang.String" scope="request"/>
<jsp:useBean id="projectName" type="java.lang.String" scope="request"/>

    <h1>Plugin ${name}</h1>
    <h1>Project name ${projectName}</h1>
 
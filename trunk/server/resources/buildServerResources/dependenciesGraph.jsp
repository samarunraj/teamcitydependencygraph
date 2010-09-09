<%@ include file="/include.jsp" %>

<c:set var="title" value="Test page" scope="request"/>

<jsp:useBean id="name" type="java.lang.String" scope="request"/>
<jsp:useBean id="projectId" type="java.lang.String" scope="request"/>
<jsp:useBean id="projectName" type="java.lang.String" scope="request"/>
<jsp:useBean id="successImageUrl" type="java.lang.String" scope="request"/>
<jsp:useBean id="errorImageUrl" type="java.lang.String" scope="request"/>
<jsp:useBean id="json" type="java.lang.String" scope="request"/>

    <h1>Plugin ${name}</h1>
    <h1>Project name ${projectName}</h1>

    <div style="display: none">
        <img src="${successImageUrl}" height="16" width="16" border="0">
        <img src="${errorImageUrl}" height="16" width="16" border="0">
    </div>

                  <pre>
                  ${json}
                  </pre>
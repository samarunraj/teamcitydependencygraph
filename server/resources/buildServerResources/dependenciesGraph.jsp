<%@ include file="/include.jsp" %>

<c:set var="title" value="Test page" scope="request"/>

<jsp:useBean id="successImageUrl" type="java.lang.String" scope="request"/>
<jsp:useBean id="errorImageUrl" type="java.lang.String" scope="request"/>
<jsp:useBean id="json" type="java.lang.String" scope="request"/>

<!-- CSS Files -->
<link type="text/css" href="${teamcityPluginResourcesPath}css/base.css" rel="stylesheet" />
<link type="text/css" href="${teamcityPluginResourcesPath}css/ForceDirected.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="${teamcityPluginResourcesPath}js/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src=".${teamcityPluginResourcesPath}js/jit-yc.js"></script>

<script language="javascript" type="text/javascript" src=".${teamcityPluginResourcesPath}js/dependenciesGraph.js"></script>

    <div style="display: none">
        <img id="successImage" src="${successImageUrl}" height="16" width="16" border="0">
        <img id="errorImage" src="${errorImageUrl}" height="16" width="16" border="0">
    </div>


<div id="container">

<div id="left-container">

<div id="id-list"></div>


</div>

<div id="center-container">
    <div id="infovis"></div>
</div>

<div id="right-container">

<div id="inner-details"></div>

</div>

<div id="log"></div>

<script type="text/javascript">
    var json = ${json}.nodes;
    init(json);
</script>
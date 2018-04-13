<%@ include file="init.jsp" %>
 
<div id="<portlet:namespace />"></div>
  
<aui:script require="angular-npm-portlet@1.0.0">
	// Pass the portlet's namespace to the Javascript bootstrap method so that
	// it can attach the boostrap Angular component to the above div tag.
	angularNpmPortlet100.default('#<portlet:namespace />');
</aui:script>
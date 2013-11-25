<%-- 
    Document   : success
    Created on : Feb 28, 2009, 8:24:14 AM
    Author     : eswar@vaannila.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello DT354</title>
    </head>
    <body>
        <h1><s:property value="responseButton" /></h1>
		 <s:form action="accessDatabase" >
            <s:textfield name="userName" label="User Name" />
			<s:textfield name="password" label="Password" />
            <s:submit name="responseButton" value="Log In" label="Log In" />
        </s:form>
    </body>
</html>

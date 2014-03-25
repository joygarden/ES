<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
    <title><decorator:title default="易销"/></title>
    <decorator:head/>
</head>
<body>
<h1>Header</h1>
<p><b>Navigation</b></p>
<hr />
<decorator:body />
<hr />
<footer>
    <h1>footer</h1>
</footer>
</body>
</html>
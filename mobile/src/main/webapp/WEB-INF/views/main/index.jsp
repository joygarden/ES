<%--
  Created by IntelliJ IDEA.
  User: liaozhisong
  Date: 3/22/14
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="${basePath}static/js/jquery.min.js"></script>
    <title></title>
</head>
<body>
    index haha!!
    ${message.totalPageCount}<br/>
    ${message.total}
    <form action="${basePath}main/upload" method="post" enctype="multipart/form-data" >
        <input type="file" name="file" />
        <input type="submit" />
    </form>
</body>
</html>

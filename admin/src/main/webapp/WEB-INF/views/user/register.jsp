<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
</head>
<body>
<form class="form-horizontal" action="${basePath}user/register" method="post">
    <fieldset>
        <div class="input-prepend" title="Username">
            <span class="add-on"><i class="icon-user"></i></span>
            <input class="input-large span10" name="username" value="${param.username}"
                   type="text"
                   placeholder="用户名"/>
        </div>
        <div class="clearfix"></div>
        <div class="input-prepend" title="Password">
            <span class="add-on"><i class="icon-lock"></i></span>
            <input class="input-large span10" name="password" type="password"
                   placeholder="密码"/>
        </div>
        <div class="clearfix"></div>


        <div class="btn-group button-login">
            <button type="submit" class="btn btn-primary">注册</button>
        </div>
        <div class="clearfix"></div>
    </fieldset>
</form>
</body>
</html>

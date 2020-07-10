<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<script type="text/javascript">
    function updateUserRole() {
        var id = $("input:checkbox:checked");
        var idStr = "";
        id.each(function (i, e) {
            idStr += e.value + ",";
        });
        $("#role_ids").val(idStr);
        document.getElementById("urform").submit();
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            用户管理
            <small>用户管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">
        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">用户 [${user.userName}] 角色列表</h3>
            </div>
            <div class="box-body">
                <form id="urform" action="${ctx}/system/user?operation=updateRole" method="post">
                    <input type="hidden" name="userId" value="${user.id}"/>
                    <input type="hidden" name="role_ids" value=""/>
                    <div class="textbox" id="centerTextbox">
                        <div style="text-align:left">
                            <c:forEach items="${roleList}" var="role" varStatus="vs">
                                     <span style="padding:3px;margin-right:30px;width: 160px;display: inline-block">
                                         <input type="checkbox" name="roleIds" value="${role.id}" ${role.remark}/>${role.name}
                                     </span><br/>
                            </c:forEach>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="box-tools text-center">
            <button type="button" class="btn bg-maroon" onclick='updateUserRole()'>保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
    </section>
</div>
</body>

</html>
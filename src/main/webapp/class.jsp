<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增班级</title>
    <style>
        table{border-collapse: collapse;}
        td{padding:6px;}
    </style>
</head>
<body>

<form action="<%= request.getContextPath() %>/addClazz" method="post">
    <table>
        <tr>
            <td>班级ID</td>
            <td><input type="text" name="cid"></td>
        </tr>
        <tr>
            <td>班级编号</td>
            <td><input type="text" name="cno"></td>
        </tr>
        <tr>
            <td>班级名称</td>
            <td><input type="text" name="cname"></td>
        </tr>
        <tr>
            <td>教师编号</td>
            <td><input type="text" name="tid"></td>
        </tr>
        <tr>
            <td><input type="submit" value="添加"></td>
            <td><input type="reset" value="重置"></td>
        </tr>

    </table>


</form>
</body>
</html>

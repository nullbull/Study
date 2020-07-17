<%--
  Created by IntelliJ IDEA.
  User: 11291
  Date: 2018/3/12
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>保存商品的页面</h1>
<s:form  namespace="/niu" method="post" theme="simple" action="product_save">
    <table>
        <tr>
            <td>商品名称</td>
            <td><s:textfield name="pname"/></td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><s:textfield name="price"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit"></td>
        </tr>
    </table>
</s:form>
</body>
</html>

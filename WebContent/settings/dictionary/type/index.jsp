<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	Boolean deleteFailed = (Boolean) request.getSession().getAttribute("deleteFailed");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript"
	src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		if (
<%=deleteFailed%>
	) {
			alert("删除失败,包含不可删除数据");
		}
<%request.getSession().removeAttribute("deleteFailed");%>
	$("#ckOper").change(function() {
			$(":checkbox[name=ck]").prop("checked", $(this).prop("checked"));
			checkButton();
		});

		$("#addButton").click(function() {
			window.location.href = 'settings/dictionary/type/save.jsp'
		});

		$("#editButton")
				.click(
						function() {
							var code = $($(":checkbox[name=ck]:checked")[0])
									.val();
							window.location.href = "settings/dictionary/type/edit.do?code="
									+ code;
						});

		$("#deleteButton").click(function() {
			if (confirm("是否要删除?")) {
				var $codes = $(":checkbox[name=ck]:checked");
				var uri = "settings/dictionary/type/delete.do?";
				uri += $codes.map(function() {
					return "code=" + $(this).val();
				}).get().join("&");

				window.location.href = uri;
			}
		});

		$("#dictTypeList > tr:even").addClass("active");

	});
</script>
</head>
<body>
	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典类型列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar"
		style="background-color: #F7F7F7; height: 50px; position: relative; left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
			<button id="addButton" type="button" class="btn btn-primary">
				<span class="glyphicon glyphicon-plus"></span> 创建
			</button>
			<button id="editButton" type="button" class="btn btn-default"
				disabled="disabled">
				<span class="glyphicon glyphicon-edit"></span> 编辑
			</button>
			<button id="deleteButton" type="button" class="btn btn-danger"
				disabled="disabled">
				<span class="glyphicon glyphicon-minus"></span> 删除
			</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" id="ckOper" /></td>
					<td>序号</td>
					<td>编码</td>
					<td>名称</td>
					<td>描述</td>
				</tr>
			</thead>
			<tbody id="dictTypeList">
				<c:if test="${empty requestScope.dictTypeList}">
					<tr>
						<td colspan='5' align="center">没有数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty requestScope.dictTypeList}">
					<c:forEach items="${requestScope.dictTypeList}" var="dictType"
						varStatus="loop">


						<tr>
							<td><input type="checkbox" name="ck"
								value="${dictType.code}" onchange="checkAll(this)" /></td>
							<td>${loop.count}</td>
							<td>${dictType.code}</td>
							<td>${dictType.name}</td>
							<td>${dictType.description}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		function checkAll(dom) {
			if ($(dom).prop("checked") == false) {
				$("#ckOper").prop("checked", false);
			} else {
				if ($(":checkbox[name=ck]:checked").size() == $(
						":checkbox[name=ck]").size()) {
					$("#ckOper").prop("checked", true);
				}
			}
			checkButton();
		}

		function checkButton() {
			if ($(":checkbox[name=ck]:checked").size() == 0) {
				$("#editButton").prop("disabled", true);
				$("#deleteButton").prop("disabled", true);
			} else if ($(":checkbox[name=ck]:checked").size() == 1) {
				$("#editButton").prop("disabled", false);
				$("#deleteButton").prop("disabled", false);
			} else {
				$("#editButton").prop("disabled", true);
				$("#deleteButton").prop("disabled", false);
			}
		}
	</script>
</body>
</html>
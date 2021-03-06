<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<link
	href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
	type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript"
	src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(
		function() {
			$("#saveButton").click(
					function() {
						if($("#create-dicTypeCode").val() == "--请选择--") {
							alert("请指定字典类型编码");
							return false;
						} else if($("#create-dicValue").val() == "" || $("#create-dicValue").val() == null){
							alert("请填写字典值");
							return false;
						} else {
							$("#saveForm").submit();
						}
					}		
				);
		}		
	);
</script>
</head>
<body>
		<div style="position: relative; left: 30px;">
			<h3>新增字典值</h3>
			<div style="position: relative; top: -40px; left: 70%;">
				<button id="saveButton" type="button" class="btn btn-primary">保存</button>
				<button type="button" class="btn btn-default"
					onclick="window.history.back();">取消</button>
			</div>
			<hr style="position: relative; top: -40px;">
		</div>
	<form id="saveForm" class="form-horizontal" role="form"
		action="settings/dictionary/value/save.do">
		<div class="form-group">
			<label for="create-dicTypeCode" class="col-sm-2 control-label">字典类型编码<span
				style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="create-dicTypeCode"
					style="width: 200%;" name="typeCode">
					<option>--请选择--</option>
					<c:forEach items="${requestScope.dictTypeList}" var="dictType">
						<option value="${dictType.code}">${dictType.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="create-dicValue" class="col-sm-2 control-label">字典值<span
				style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-dicValue"
					name="value" style="width: 200%;">
			</div>
		</div>

		<div class="form-group">
			<label for="create-text" class="col-sm-2 control-label">文本</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-text" name="text"
					style="width: 200%;">
			</div>
		</div>

		<div class="form-group">
			<label for="create-orderNo" class="col-sm-2 control-label">排序号</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-orderNo"
					name="orderNo" style="width: 200%;">
			</div>
		</div>
	</form>

	<div style="height: 200px;"></div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					if($("#code").val().match("^[a-zA-Z0-9]+$") == null) {
						alert("编码作为主键,不能为中文,不能为空");
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
			<h3>新增字典类型</h3>
			<div style="position: relative; top: -40px; left: 70%;">
				<button id="saveButton" type="button" class="btn btn-primary">保存</button>
				<button type="button" class="btn btn-default"
					onclick="window.history.back();">取消</button>
			</div>
			<hr style="position: relative; top: -40px;">
		</div>


	<form id="saveForm" method="post" class="form-horizontal" role="form"
		action="settings/dictionary/type/add.do">
		<div class="form-group">
			<label for="create-code" class="col-sm-2 control-label">编码<span
				style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="code" name="code"
					style="width: 200%;" placeholder="编码作为主键，不能是中文">
			</div>
		</div>

		<div class="form-group">
			<label for="create-name" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="name" name="name"
					style="width: 200%;">
			</div>
		</div>

		<div class="form-group">
			<label for="create-describe" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 300px;">
				<textarea class="form-control" rows="3" id="describe"
					name="description" style="width: 200%;"></textarea>
			</div>
		</div>
	</form>

	<div style="height: 200px;"></div>
</body>
</html>
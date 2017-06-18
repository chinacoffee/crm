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

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript"
	src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var pageNum = 1;
	var pageSize = 10;
	var recordCount = 0;
	var maxPageNum = 1;
	var getListUrl = "settings/qx/role/list.do";
	var getCountUrl = "settings/qx/role/count.do";
	
	
	$(function() {
		getList(getListUrl + "?pageNo=1&pageSize=" + pageSize);
		
		$("#createRoleSave").click(
				function() {
					var code = $("#createRoleForm #create-roleCode").val();
					var name = $("#createRoleForm #create-roleName").val();
					if (code.match("^[0-9]{3}$") == null) {
						alert("编号为三位数字，不能为空，具有唯一性");
						return false;
					}
					if (name == "" ) {
						alert("名称不可为空");
						return false;
					}

					$.get("settings/qx/role/checkAdd.do?code=" + code,
							function(isAvailable) {
								if (isAvailable == 'false') {
									alert("该编码已经被使用");
									return false;
								}
								var str_data = $("#createRoleForm input").map(
										function() {
											return $(this).attr("name") + "="
													+ $(this).val();
										}).get().join("&");
								str_data += "&"
										+ $("#createRoleForm textarea").attr(
												"name") + "="
										+ $("#createRoleForm textarea").val();
								$
										.post("settings/qx/role/add.do",
												str_data, function() {
													$("#createRoleModal")
															.modal("hide");
													getList(getListUrl
												+ "?pageNo=1&pageSize="
												+ pageSize);
												});
							});

				});
		
		$("#deleteButton").click(function() {
			if (confirm("是否要删除?")) {
				var $codes = $(":checkbox[name=ck]:checked");
				var uri = "settings/qx/role/delete.do?";
				for (var i = 0; i < $codes.length; i++) {
					if (i == 0) {
						uri += "code=" + $($codes[i]).val();
					} else {
						uri += "&code=" + $($codes[i]).val();

					}
				}
				$.get(uri, function(htmlobj) {
					getList(getListUrl + "?pageNo=1&pageSize=" + pageSize);
				});
			}
		});
		
		/* 复用代码开始 */
		$("#nextPageLink").click(
				function() {
					if (pageNum == maxPageNum) {
						return;
					}
					pageNum++;
					changePage();
				});

		$("#periousPageLink").click(
				function() {
					if (pageNum == 1) {
						return;
					}
					pageNum -= 1;
					changePage();
				});

		$("#firstPageLink").click(
				function() {
					if (pageNum == 1) {
						return;
					}
					pageNum = 1;
					changePage();
				});

		$("#lastPageLink").click(
				function() {
					if (pageNum == maxPageNum) {
						return;
					}
					pageNum = maxPageNum;
					changePage();
				});


		$("#ckOper").change(function() {
			$(":checkbox[name=ck]").prop("checked", $(this).prop("checked"));
			checkButton();
		});
		/* 复用代码结束 */
		
	}); /* end */
	
</script>
</head>
<body>
	<!-- 创建角色的模态窗口 -->
	<div class="modal fade" id="createRoleModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增角色</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form" id="createRoleForm">

						<div class="form-group">
							<label for="create-roleCode" class="col-sm-2 control-label">代码<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-roleCode" name="code"
									style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-roleName" class="col-sm-2 control-label">名称<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-roleName" name="name"
									style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 65%;">
								<textarea class="form-control" rows="3" id="create-describe" name="description"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="createRoleSave">保存</button>
				</div>
			</div>
		</div>
	</div>


	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>角色列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar"
		style="background-color: #F7F7F7; height: 50px; position: relative; left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#createRoleModal">
				<span class="glyphicon glyphicon-plus"></span> 创建
			</button>
			<button type="button" class="btn btn-danger" id="deleteButton">
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
					<td>代码</td>
					<td>名称</td>
					<td>描述</td>
				</tr>
			</thead>
			<tbody id="dataList">
				<!-- <tr class="active">
					<td><input type="checkbox" /></td>
					<td>1</td>
					<td><a href="detail.html" style="text-decoration: none;">001</a></td>
					<td>管理员</td>
					<td>管理员为最高角色，拥有所有许可</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td>2</td>
					<td><a href="detail.html" style="text-decoration: none;">002</a></td>
					<td>销售总监</td>
					<td>销售总监销售总监销售总监销售总监销售总监</td>
				</tr>
				<tr class="active">
					<td><input type="checkbox" /></td>
					<td>3</td>
					<td><a href="detail.html" style="text-decoration: none;">003</a></td>
					<td>市场总监</td>
					<td>市场总监市场总监市场总监市场总监</td>
				</tr> -->
			</tbody>
		</table>
	</div>

	<div style="height: 50px; position: relative; top: 0px; left: 30px;">
		<div>
			<button type="button" class="btn btn-default"
				style="cursor: default;">
				共<b id="recordCount">50</b>条记录
			</button>
		</div>
		<div class="btn-group"
			style="position: relative; top: -34px; left: 110px;">
			<button type="button" class="btn btn-default"
				style="cursor: default;">显示</button>
			<div class="btn-group">
				<button id="pageSizeButton" type="button"
					class="btn btn-default dropdown-toggle" data-toggle="dropdown">
					10 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a onclick="changePageSize(this)">10</a></li>
					<li><a onclick="changePageSize(this)">20</a></li>
					<li><a onclick="changePageSize(this)">30</a></li>
				</ul>
			</div>
			<button type="button" class="btn btn-default"
				style="cursor: default;">条/页</button>
		</div>
		<div style="position: relative; top: -88px; left: 285px;">
			<nav>
			<ul class="pagination">
				<li id="firstPageLink"><a>首页</a></li>
				<li id="periousPageLink"><a>上一页</a></li>
				<li id="nextPageLink"><a>下一页</a></li>
				<li id="lastPageLink"><a>末页</a></li>
			</ul>
			</nav>
		</div>
	</div>
	<script type="text/javascript">
	
		/* 复用代码开始 */
		function checkChangePageLink() {
			if (pageNum == maxPageNum) {
				$("#nextPageLink").addClass("disabled");
				$("#lastPageLink").addClass("disabled");
			} else if (pageNum < maxPageNum) {
				$("#nextPageLink").removeClass("disabled");
				$("#lastPageLink").removeClass("disabled");
			}
			if (pageNum == 1) {
				$("#periousPageLink").addClass("disabled");
				$("#firstPageLink").addClass("disabled");
			} else if (pageNum > 1) {
				$("#periousPageLink").removeClass("disabled");
				$("#firstPageLink").removeClass("disabled");
			}

		}

		function checkStatus() {
			checkChangePageLink();
			$("#dataList > tr:even").addClass("active");
			checkButton();
		}

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

		function changePageSize(dom) {
			pageSize = $(dom).text();
			$("#pageSizeButton").text(pageSize);
			getList(getListUrl + "?pageNo=1&pageSize=" + pageSize);
		}

		function changePage() {
			var url = getListUrl + "?pageNo=" + pageNum + "&pageSize="
					+ pageSize;
			getList(url);
		}

		/* 复用代码结束 */

		function getList(url) {
			$
					.getJSON(
							url,
							function(resultJson) {
								var $dataList = $("#dataList");
								var pageInfo = resultJson[0].pageInfo;
								pageNum = pageInfo.pageNo;
								pageSize = pageInfo.pageSize;
								recordCount = pageInfo.totalRecords;
								maxPageNum = pageInfo.totalPages;
								$("#recordCount").text(recordCount);

								var jsonArray = resultJson[1];
								if (jsonArray.length == 0) {
									$dataList
											.html("<tr>"
													+ "<td colspan='6' align='center'>没有数据</td>"
													+ "</tr>");
								} else {
									$dataList.html("");
									$(jsonArray)
											.each(
													function(index, obj) {
														$dataList
																.append("<tr>"
																		+ "<td>"
																		+ "<input type='checkbox' name='ck' value='"
																		+ obj.code
																		+ "' onchange='checkAll(this)' />"
																		+ "</td>"
																		+ "<td>"
																		+ (index + 1)
																		+ "</td>"
																		+ "<td>"
																		+ "<a href='detail.html' style='text-decoration: none;'>"
																		+ obj.code
																		+ "</a>"
																		+ "</td>"
																		+ "<td>"
																		+ obj.name
																		+ "</td>"
																		+ "<td>"
																		+ obj.description
																		+ "</td>"
																		+ "</tr>");
													});
								}
								checkStatus();
							});
		}
	</script>
</body>
</html>
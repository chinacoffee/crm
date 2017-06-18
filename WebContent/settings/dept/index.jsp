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
	var getListUrl = "settings/dept/list.do";
	var getCountUrl = "settings/dept/count.do";
	var editUrl = "settings/dept/edit.do";
	var deleteUrl = "settings/dept/delete.do";
	var addUrl = "settings/dept/add.do";
	var checkAddUrl = "settings/dept/checkAdd.do";
	var updateUrl = "settings/dept/update.do";

	$(function() {

		getList(getListUrl + "?pageNo=1&pageSize=" + pageSize);

		$("#editButton").click(
				function() {
					var uri = editUrl + "?id="
							+ $($(":checkbox[name=ck]:checked")[0]).val()

					$.getJSON(uri, function(jsonObj) {
						$("#edit-create-id").val(jsonObj.dept.id);
						$("#edit-create-code").val(jsonObj.dept.code);
						$("#edit-create-name").val(jsonObj.dept.name);
						$("#edit-create-manager").val(jsonObj.dept.manager);
						$("#edit-create-phone").val(jsonObj.dept.phone);
						$("#edit-create-describe").val(jsonObj.dept.description);
					});

				});

		$("#deleteButton").click(function() {
			if (confirm("是否要删除?")) {
				var $codes = $(":checkbox[name=ck]:checked");
				var url = deleteUrl + "?";
				var str_data = $codes.map(function() {
					return "id=" + $(this).val();
				}).get().join("&");

				$.get(url + str_data, function() {
					getList(getListUrl + "?pageNo=1&pageSize=" + pageSize);
				});
			}
		});

		$("#createSaveButton").click(
				function() {
					var code = $("#create-code").val()
					if (code.match("^[0-9]{4}$") == null) {
						alert("编号为四位数字，不能为空，具有唯一性");
						return false;
					} else {
						$.get(checkAddUrl + "?code=" + code,
								function(available) {
									if (available == 'false') {
										alert("该编码已经被使用");
										return false;
									}
									var str_data = $("#createFrom input").map(
											function() {
												return $(this).attr("name")
														+ "=" + $(this).val();
											}).get().join("&");
									str_data += "&"
											+ $("#createFrom textarea").attr(
													"name") + "="
											+ $("#createFrom textarea").val();
									$.post(addUrl, str_data, function() {
										$("#createDeptModal").modal("hide");
										getList(getListUrl
												+ "?pageNo=1&pageSize="
												+ pageSize);
									});
								});
					}

				});

		$("#editUpdateButton")
				.click(
						function() {
							var str_data = $("#editForm input").map(
									function() {
										return $(this).attr("name") + "="
												+ $(this).val();
									}).get().join("&");
							str_data += "&"
									+ $("#editForm textarea").attr("name")
									+ "=" + $("#editForm textarea").val();
							$
									.post(
											updateUrl,
											str_data,
											function() {
												var $row = $("#dataList tr:has(:checkbox[name=ck]:checked)");
												var result = "<td>"
														+ "<input type='checkbox' name='ck' value='"
														+ $(
																"#editForm input[name=code]")
																.val()
														+ "' onchange='checkAll(this)'/>"
														+ "</td>"
														+ "<td>"
														+ $(
																"#editForm input[name=code]")
																.val()
														+ "</td>"
														+ "<td>"
														+ $(
																"#editForm input[name=name]")
																.val()
														+ "</td>"
														+ "<td>"
														+ $(
																"#editForm input[name=manager]")
																.val()
														+ "</td>"
														+ "<td>"
														+ $(
																"#editForm input[name=phone]")
																.val()
														+ "</td>"
														+ "<td>"
														+ $(
																"#editForm textarea[name=description]")
																.val()
														+ "</td>";
												$row.html(result);
												$row.addClass("success");
												checkStatus();
											});
						});

		$("body").click(function() {
			$("#dataList tr").removeClass("success");
		});

		$("#nextPageLink").click(function() {
			if (pageNum == maxPageNum) {
				return;
			}
			pageNum++;
			changePage();
		});

		$("#periousPageLink").click(function() {
			if (pageNum == 1) {
				return;
			}
			pageNum -= 1;
			changePage();
		});

		$("#firstPageLink").click(function() {
			if (pageNum == 1) {
				return;
			}
			pageNum = 1;
			changePage();
		});

		$("#lastPageLink").click(function() {
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

	});
</script>
</head>
<body>
	<!-- 我的资料 -->
	<div class="modal fade" id="myInformation" role="dialog">
		<div class="modal-dialog" role="document" style="width: 30%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">我的资料</h4>
				</div>
				<div class="modal-body">
					<div style="position: relative; left: 40px;">
						姓名：<b>张三</b><br> <br> 登录帐号：<b>zhangsan</b><br> <br>
						组织机构：<b>1005，市场部，二级部门</b><br> <br> 邮箱：<b>zhangsan@bjpowernode.com</b><br>
						<br> 失效时间：<b>2017-02-14 10:10:10</b><br> <br>
						允许访问IP：<b>127.0.0.1,192.168.100.2</b>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 修改密码的模态窗口 -->
	<div class="modal fade" id="editPwdModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 70%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="oldPwd" class="col-sm-2 control-label">原密码</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="oldPwd"
									style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="newPwd" class="col-sm-2 control-label">新密码</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="newPwd"
									style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="confirmPwd" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="confirmPwd"
									style="width: 200%;">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="window.location.href='../login.html';">更新</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 退出系统的模态窗口 -->
	<div class="modal fade" id="exitModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 30%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">离开</h4>
				</div>
				<div class="modal-body">
					<p>您确定要退出系统吗？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="window.location.href='login.html';">确定</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 顶部 -->
	<div id="top"
		style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div
			style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
			CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span>
		</div>
		<div style="position: absolute; top: 15px; right: 15px;">
			<ul>
				<li class="dropdown user-dropdown"><a href="javascript:void(0)"
					style="text-decoration: none; color: white;"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user"></span> zhangsan <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="workbench/index.html"><span
								class="glyphicon glyphicon-home"></span> 工作台</a></li>
						<li><a href="settings/index.html"><span
								class="glyphicon glyphicon-wrench"></span> 系统设置</a></li>
						<li><a href="javascript:void(0)" data-toggle="modal"
							data-target="#myInformation"><span
								class="glyphicon glyphicon-file"></span> 我的资料</a></li>
						<li><a href="javascript:void(0)" data-toggle="modal"
							data-target="#editPwdModal"><span
								class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
						<li><a href="javascript:void(0);" data-toggle="modal"
							data-target="#exitModal"><span
								class="glyphicon glyphicon-off"></span> 退出</a></li>
					</ul></li>
			</ul>
		</div>
	</div>

	<!-- 创建部门的模态窗口 -->
	<div class="modal fade" id="createDeptModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-plus"></span> 新增部门
					</h4>
				</div>
				<div class="modal-body">

					<form id="createFrom" class="form-horizontal" role="form"
						action="settings/dept/add.do" method="post">

						<div class="form-group">
							<label for="create-code" class="col-sm-2 control-label">编号<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-code"
									name="code" style="width: 200%;"
									placeholder="编号为四位数字，不能为空，具有唯一性">
							</div>
						</div>

						<div class="form-group">
							<label for="create-name" class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-name"
									name="name" style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-manager" class="col-sm-2 control-label">负责人</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-manager"
									name="manager" style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone"
									name="phone" style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 55%;">
								<textarea class="form-control" rows="3" id="create-describe"
									name="description"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="createSaveButton" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改部门的模态窗口 -->
	<div class="modal fade" id="editDeptModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-edit"></span> 编辑部门
					</h4>
				</div>
				<div class="modal-body">

					<form id="editForm" class="form-horizontal" role="form"
						action="settings/dept/update.do" method="post">
						<input type="hidden" id="edit-create-id"
							name="id" value="1110">
						<div class="form-group">
							<label for="create-code" class="col-sm-2 control-label">编号<span
								style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-create-code"
									name="code" readonly="readonly" style="width: 200%;"
									placeholder="编号为四位数字，不能为空，具有唯一性" value="1110">
							</div>
						</div>

						<div class="form-group">
							<label for="create-name" class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-create-name"
									name="name" style="width: 200%;" value="财务部">
							</div>
						</div>

						<div class="form-group">
							<label for="create-manager" class="col-sm-2 control-label">负责人</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-create-manager"
									name="manager" style="width: 200%;" value="张飞">
							</div>
						</div>

						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-create-phone"
									name="phone" style="width: 200%;" value="010-84846004">
							</div>
						</div>

						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 55%;">
								<textarea class="form-control" rows="3" name="description"
									id="edit-create-describe">description info</textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="editUpdateButton" class="btn btn-primary"
						data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>

	<div style="width: 95%">
		<div>
			<div style="position: relative; left: 30px; top: -10px;">
				<div class="page-header">
					<h3>部门列表</h3>
				</div>
			</div>
		</div>
		<div class="btn-toolbar" role="toolbar"
			style="background-color: #F7F7F7; height: 50px; position: relative; left: 30px; top: -30px;">
			<div class="btn-group" style="position: relative; top: 18%;">
				<button id="addButton" type="button" class="btn btn-primary"
					data-toggle="modal" data-target="#createDeptModal">
					<span class="glyphicon glyphicon-plus"></span> 创建
				</button>
				<button id="editButton" type="button" class="btn btn-default"
					data-toggle="modal" data-target="#editDeptModal"
					disabled="disabled">
					<span class="glyphicon glyphicon-edit"></span> 编辑
				</button>

				<button id="deleteButton" type="button" class="btn btn-danger"
					disabled="disabled">
					<span class="glyphicon glyphicon-minus"></span> 删除
				</button>
			</div>
		</div>
		<div style="position: relative; left: 30px; top: -10px;">
			<table class="table table-hover">
				<thead>
					<tr style="color: #B3B3B3;">
						<td><input type="checkbox" id="ckOper" /></td>
						<td>编号</td>
						<td>名称</td>
						<td>负责人</td>
						<td>电话</td>
						<td>描述</td>
					</tr>
				</thead>
				<tbody id="dataList">
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

	</div>
	<script type="text/javascript">
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
																		+ obj.dept.id
																		+ "' onchange='checkAll(this)' />"
																		+ "</td>"
																		+ "<td>"
																		+ obj.dept.code
																		+ "</td>"
																		+ "<td>"
																		+ obj.dept.name
																		+ "</td>"
																		+ "<td>"
																		+ obj.dept.manager
																		+ "</td>"
																		+ "<td>"
																		+ obj.dept.phone
																		+ "</td>"
																		+ "<td>"
																		+ obj.dept.description
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
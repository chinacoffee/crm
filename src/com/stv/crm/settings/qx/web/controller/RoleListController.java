package com.stv.crm.settings.qx.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.qx.domain.Role;
import com.stv.crm.settings.qx.service.RoleService;
import com.stv.crm.settings.qx.service.impl.RoleServiceImpl;
import com.stv.crm.util.PageInfo;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class RoleListController
 */
public class RoleListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}

	/**
	 * 功能:获取tbl_dictionary_type表中的信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		
		RoleService roleService = (RoleService) ServiceFactory.getService(new RoleServiceImpl());
		
		Map<String, Object> resultMap = roleService.getAll(pageNoStr, pageSizeStr);
		
		PageInfo pageInfo = (PageInfo) resultMap.get("pageInfo");
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>) resultMap.get("list");
		List<String> roleStrList = new ArrayList<>();
		for (Role role : roleList) {
			roleStrList.add(role.toJson());
		}
		String resultJson = "[" + pageInfo.toJson() + ", " + roleStrList + "]";
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(resultJson);
		pw.close();

	}

}

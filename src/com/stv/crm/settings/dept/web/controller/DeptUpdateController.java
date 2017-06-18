package com.stv.crm.settings.dept.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dept.domain.Dept;
import com.stv.crm.settings.dept.servcie.DeptService;
import com.stv.crm.settings.dept.servcie.impl.DeptServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DeptUpdateController
 */
public class DeptUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}

	/**
	 * 功能:更新字典表类型表,并跳转到显示页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String manager = request.getParameter("manager");
		String phone = request.getParameter("phone");
		String description = request.getParameter("description");
		Dept dept = new Dept();
		dept.setId(id);
		dept.setCode(code);
		dept.setName(name);
		dept.setManager(manager);
		dept.setPhone(phone);
		dept.setDescription(description);
		
		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		
		deptService.update(dept);

	}
}

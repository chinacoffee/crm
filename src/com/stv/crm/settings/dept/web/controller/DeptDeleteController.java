package com.stv.crm.settings.dept.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dept.servcie.DeptService;
import com.stv.crm.settings.dept.servcie.impl.DeptServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DeptDeleteController
 */
public class DeptDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}

	/**
	 * 删除选中的数据,并跳转到显示页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		
		deptService.delete(id);

		response.sendRedirect(request.getContextPath() + "/settings/dept/index.jsp");
	}
}

package com.stv.crm.settings.dept.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dept.domain.Dept;
import com.stv.crm.settings.dept.servcie.DeptService;
import com.stv.crm.settings.dept.servcie.impl.DeptServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DeptEditController
 */
public class DeptEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		
		doGet(req, resp);
	}

	/**
	 * 功能:按照code获取一个数据,然后重定向到编译页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		
		Dept dept = deptService.getById(id);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(dept.toJson());
		pw.close();
		
	}

}

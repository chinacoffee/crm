package com.stv.crm.settings.dept.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dept.servcie.DeptService;
import com.stv.crm.settings.dept.servcie.impl.DeptServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DeptCheckAddController
 */
public class DeptCheckAddController extends HttpServlet {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		
		boolean available = deptService.checkCodeAvailable(code);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(String.valueOf(available));
		pw.close();
	}

}

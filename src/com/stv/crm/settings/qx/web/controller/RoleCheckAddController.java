package com.stv.crm.settings.qx.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.qx.service.RoleService;
import com.stv.crm.settings.qx.service.impl.RoleServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class RoleCheckAddController
 */
public class RoleCheckAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}
	
	/**
	 * 功能:
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		RoleService service = (RoleService) ServiceFactory.getService(new RoleServiceImpl());
		boolean available = service.checkCode(code);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(String.valueOf(available));
		pw.close();
	
	}

}

package com.stv.crm.settings.qx.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.qx.domain.Role;
import com.stv.crm.settings.qx.service.RoleService;
import com.stv.crm.settings.qx.service.impl.RoleServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class RoleAddController
 */
public class RoleAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}

	/**
	 * 功能:添加数据到数据库,并跳转到展示页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Role role = new Role();
		role.setCode(code);
		role.setName(name);
		role.setDescription(description);
		
		RoleService service = (RoleService) ServiceFactory.getService(new RoleServiceImpl());
		service.save(role);

	}

}

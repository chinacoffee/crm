package com.stv.crm.settings.qx.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.qx.service.RoleService;
import com.stv.crm.settings.qx.service.impl.RoleServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class RoleDeleteController
 */
public class RoleDeleteController extends HttpServlet {
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
		String[] codes = request.getParameterValues("code");
		
		RoleService service = (RoleService) ServiceFactory.getService(new RoleServiceImpl());
		service.delete(codes);

		response.sendRedirect(request.getContextPath() + "/settings/qx/role/index.jsp");
	}

}

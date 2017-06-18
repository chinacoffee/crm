package com.stv.crm.settings.dictionary.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dictionary.domain.DictionaryType;
import com.stv.crm.settings.dictionary.service.DictionaryTypeService;
import com.stv.crm.settings.dictionary.service.impl.DictionaryTypeServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DictTypeUpdateServlet
 */
public class DictTypeUpdateController extends HttpServlet {
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
		DictionaryType type = new DictionaryType();
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		type.setCode(code);
		type.setName(name);
		type.setDescription(description);
		
		DictionaryTypeService service = (DictionaryTypeService) ServiceFactory.getService(new DictionaryTypeServiceImpl());
		service.update(type);

		response.sendRedirect(request.getContextPath() + "/settings/dictionary/type/list.do");
	}

}

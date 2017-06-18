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
 * Servlet implementation class DictTypeFindByCodeServlet
 */
public class DictTypeEditController extends HttpServlet {
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
		// 0.局部变量
		String code = request.getParameter("code");
		DictionaryTypeService service = (DictionaryTypeService) ServiceFactory.getService(new DictionaryTypeServiceImpl());
		
		DictionaryType type = service.getByCode(code);

		request.setAttribute("dictType", type);
		/**
		 * 转发使用的是内部路径 内部路径是除掉项目名的绝对路径
		 */
		request.getRequestDispatcher("/settings/dictionary/type/edit.jsp").forward(request, response);

	}

}

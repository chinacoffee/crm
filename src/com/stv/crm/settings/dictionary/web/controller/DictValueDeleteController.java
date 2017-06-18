package com.stv.crm.settings.dictionary.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dictionary.service.DictionaryValueService;
import com.stv.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DictValueDeleteController
 */
public class DictValueDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 通过doGet处理post请求
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}

	/**
	 * 删除选中的数据,并跳转到显示页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] ids = request.getParameterValues("id");
		DictionaryValueService service = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
		service.delete(ids);

		response.sendRedirect(request.getContextPath() + "/settings/dictionary/value/list.do");
	}

}

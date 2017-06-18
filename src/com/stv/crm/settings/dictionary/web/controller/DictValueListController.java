package com.stv.crm.settings.dictionary.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dictionary.domain.DictionaryValue;
import com.stv.crm.settings.dictionary.service.DictionaryValueService;
import com.stv.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DictValueListController
 */
public class DictValueListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能:调用doGet,处理Post请求
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}

	/**
	 * 获取DictionaryValue的所有值,并把结果传给显示页面输出
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DictionaryValueService service = (DictionaryValueService) ServiceFactory
				.getService(new DictionaryValueServiceImpl());

		List<DictionaryValue> valueList = service.getAll();

		request.setAttribute("dictValueList", valueList);
		/**
		 * 转发使用的是内部路径 内部路径是除掉项目名的绝对路径
		 */
		request.getRequestDispatcher("/settings/dictionary/value/index.jsp").forward(request, response);

	}

}

package com.stv.crm.settings.dictionary.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dictionary.domain.DictionaryValue;
import com.stv.crm.settings.dictionary.service.DictionaryValueService;
import com.stv.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DictValueByCodeController
 */
public class DictValueEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 使用doGet实现Post请求
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}

	/**
	 * 功能:按照code获取一个数据,然后重定向到编译页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// 方法一:调用两次Service
		// DictionaryValueService valueService = (DictionaryValueService)
		// ServiceFactory.getService(new DictionaryValueServiceImpl());
		// DictionaryValue dictValue = valueService.getById(id);
		//
		// String code = dictValue.getTypeCode();
		// DictionaryTypeService typeService = (DictionaryTypeService)
		// ServiceFactory.getService(new DictionaryTypeServiceImpl());
		//
		// DictionaryType type = typeService.getByCode(code);

		// 方法二:调用一次service,在service层调用一次dao,但是dao的返回结果是一个复合Map,需要解析数据
		DictionaryValueService valueService = (DictionaryValueService) ServiceFactory
				.getService(new DictionaryValueServiceImpl());
		Map<String, Object> resultMap = valueService.getByIdWithCodeName(id);

		request.setAttribute("typeName", (String) resultMap.get("typeName"));
		request.setAttribute("dictValue", (DictionaryValue) resultMap.get("dictValue"));
		/**
		 * 转发使用的是内部路径 内部路径是除掉项目名的绝对路径
		 */
		request.getRequestDispatcher("/settings/dictionary/value/edit.jsp").forward(request, response);

	}

}

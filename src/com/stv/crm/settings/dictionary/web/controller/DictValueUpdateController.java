package com.stv.crm.settings.dictionary.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dictionary.domain.DictionaryValue;
import com.stv.crm.settings.dictionary.service.DictionaryValueService;
import com.stv.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DictValueUpdateController
 */
public class DictValueUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 使用doGet实现Post请求
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}
	
	/**
	 * 功能:更新字典表类型表,并跳转到显示页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DictionaryValue dictValue = new DictionaryValue();
		String id = request.getParameter("id");
		String value = request.getParameter("value");
		String text = request.getParameter("text");
		String orderNo = request.getParameter("orderNo");
		String typeCode = request.getParameter("typeCode");
		dictValue.setId(id);
		dictValue.setValue(value);
		dictValue.setText(text);
		dictValue.setOrderNo(Integer.valueOf(orderNo));
		dictValue.setTypeCode(typeCode);
		
		DictionaryValueService service = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
		service.update(dictValue);

		response.sendRedirect(request.getContextPath() + "/settings/dictionary/value/list.do");
	}

	

}

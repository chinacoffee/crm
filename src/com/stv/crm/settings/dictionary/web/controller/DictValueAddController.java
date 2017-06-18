package com.stv.crm.settings.dictionary.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dictionary.domain.DictionaryType;
import com.stv.crm.settings.dictionary.service.DictionaryTypeService;
import com.stv.crm.settings.dictionary.service.impl.DictionaryTypeServiceImpl;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DictTypeForValueListController
 */
public class DictValueAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 用doGet处理Post请求方式
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}
	
	/**
	 * 功能:获取tbl_dictionary_type表中的信息,把结果传给value表的添加页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DictionaryTypeService service = (DictionaryTypeService) ServiceFactory
				.getService(new DictionaryTypeServiceImpl());

		List<DictionaryType> typeList = service.getAll();

		request.setAttribute("dictTypeList", typeList);
		/**
		 * 转发使用的是内部路径
		 * 内部路径是除掉项目名的绝对路径
		 */
		request.getRequestDispatcher("/settings/dictionary/value/save.jsp").forward(request, response);
	
	
	}

	

}

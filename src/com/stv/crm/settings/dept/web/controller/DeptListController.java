package com.stv.crm.settings.dept.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stv.crm.settings.dept.domain.Dept;
import com.stv.crm.settings.dept.servcie.DeptService;
import com.stv.crm.settings.dept.servcie.impl.DeptServiceImpl;
import com.stv.crm.util.PageInfo;
import com.stv.crm.util.ServiceFactory;

/**
 * Servlet implementation class DeptListController
 */
public class DeptListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF8");
		doGet(req, resp);
	}
	
	/**
	 * 功能:获取tbl_dictionary_type表中的信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		
		DeptService deptService = (DeptService) ServiceFactory.getService(new DeptServiceImpl());
		
		Map<String, Object> resultMap = deptService.getAll(pageNoStr, pageSizeStr);
		
		PageInfo pageInfo = (PageInfo) resultMap.get("pageInfo");
		@SuppressWarnings("unchecked")
		List<Dept> deptList = (List<Dept>) resultMap.get("list");
		List<String> deptStrList = new ArrayList<>();
		for (Dept dept : deptList) {
			deptStrList.add(dept.toJson());
		}
		String resultJson = "[" + pageInfo.toJson() + ", " + deptStrList + "]";
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(resultJson);
		pw.close();
	
	}
	
}

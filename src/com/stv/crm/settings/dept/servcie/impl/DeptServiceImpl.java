package com.stv.crm.settings.dept.servcie.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stv.crm.settings.dept.dao.DeptDao;
import com.stv.crm.settings.dept.domain.Dept;
import com.stv.crm.settings.dept.servcie.DeptService;
import com.stv.crm.util.PageInfo;
import com.stv.crm.util.SqlSessionUtil;

public class DeptServiceImpl implements DeptService {

	@Override
	public void save(Dept dept) {
		DeptDao deptDao = SqlSessionUtil.getSession().getMapper(DeptDao.class);
		deptDao.save(dept);

	}

	@Override
	public void delete(String id) {
		 DeptDao deptDao = SqlSessionUtil.getSession().getMapper(DeptDao.class);
		 deptDao.delete(id);
	}

	@Override
	public void update(Dept dept) {
		DeptDao deptDao = SqlSessionUtil.getSession().getMapper(DeptDao.class);
		deptDao.update(dept);
	}

	@Override
	public Dept getById(String id) {
		DeptDao deptDao = SqlSessionUtil.getSession().getMapper(DeptDao.class);
		return deptDao.getById(id);
	}

	@Override
	public Map<String, Object> getAll(String pageNoStr, String pageSizeStr) {
		DeptDao deptDao = SqlSessionUtil.getSession().getMapper(DeptDao.class);
		PageInfo pageInfo = new PageInfo();
		int pageNo = 1;
		int pageSize = 10;
		int totalPages;
		int totalRecords;
		
		if(pageNoStr != null) {
			pageNo = Integer.valueOf(pageNoStr);
		}
		
		if(pageSizeStr != null) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		
		totalRecords = deptDao.getTotalCount();
		totalPages = (totalRecords % pageSize == 0) ? totalRecords / pageSize : totalRecords / pageSize + 1;
		
		pageInfo.setPageNo(pageNo);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotalPages(totalPages);
		pageInfo.setTotalRecords(totalRecords);
		
		Map<String, Integer> page = new HashMap<>();
		page.put("skipCount", pageSize*(pageNo - 1));
		page.put("pageSize", pageSize);
		
		List<Dept> deptList = deptDao.getAll(page);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", deptList);
		resultMap.put("pageInfo", pageInfo);
		
		return resultMap;
	}

	@Override
	public boolean checkCodeAvailable(String code) {
		DeptDao deptDao = SqlSessionUtil.getSession().getMapper(DeptDao.class);
		int count = deptDao.checkCode(code);
		return (count == 0) ? true : false;
	}

}
















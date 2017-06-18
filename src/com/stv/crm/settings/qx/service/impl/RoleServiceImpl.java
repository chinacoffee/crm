package com.stv.crm.settings.qx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stv.crm.settings.qx.dao.RoleDao;
import com.stv.crm.settings.qx.domain.Role;
import com.stv.crm.settings.qx.service.RoleService;
import com.stv.crm.util.PageInfo;
import com.stv.crm.util.SqlSessionUtil;

public class RoleServiceImpl implements RoleService {

	@Override
	public void save(Role role) {
		RoleDao dao = SqlSessionUtil.getSession().getMapper(RoleDao.class);
		dao.save(role);
	}

	@Override
	public void delete(String... codes) {
		RoleDao dao = SqlSessionUtil.getSession().getMapper(RoleDao.class);
		for (String code : codes) {
			dao.delete(code);
		}
	}

	@Override
	public Role getByCode(String code) {
		RoleDao dao = SqlSessionUtil.getSession().getMapper(RoleDao.class);
		return dao.getByCode(code);
	}

	@Override
	public Map<String, Object> getAll(String pageNoStr, String pageSizeStr) {
		RoleDao dao = SqlSessionUtil.getSession().getMapper(RoleDao.class);
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
		
		totalRecords = dao.getTotal();
		totalPages = (totalRecords % pageSize == 0) ? totalRecords / pageSize : totalRecords / pageSize + 1;
		
		pageInfo.setPageNo(pageNo);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotalPages(totalPages);
		pageInfo.setTotalRecords(totalRecords);
		
		Map<String, Integer> page = new HashMap<>();
		page.put("skipCount", pageSize*(pageNo - 1));
		page.put("pageSize", pageSize);
		
		List<Role> deptList = dao.getAll(page);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", deptList);
		resultMap.put("pageInfo", pageInfo);
		
		return resultMap;
	}

	@Override
	public boolean checkCode(String code) {
		RoleDao dao = SqlSessionUtil.getSession().getMapper(RoleDao.class);
		int count = dao.checkCode(code);
		return (count == 0) ? true : false;
	}

}

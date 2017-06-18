package com.stv.crm.settings.dept.servcie;

import java.util.Map;

import com.stv.crm.settings.dept.domain.Dept;

public interface DeptService {

	void save(Dept dept);

	void delete(String id);

	void update(Dept dept);

	Dept getById(String id);

	Map<String, Object> getAll(String pageNoStr, String pageSizeStr);
	
	boolean checkCodeAvailable(String code);

}

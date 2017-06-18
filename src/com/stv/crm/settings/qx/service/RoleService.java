package com.stv.crm.settings.qx.service;

import java.util.Map;

import com.stv.crm.settings.qx.domain.Role;

public interface RoleService {
	
	void save(Role role);
	
	void delete(String... codes);
	
	Role getByCode(String code);
	
	Map<String, Object> getAll(String pageNoStr, String pageSizeStr);
	
	boolean checkCode(String code);
	
}

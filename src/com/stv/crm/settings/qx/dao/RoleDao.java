package com.stv.crm.settings.qx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.stv.crm.settings.qx.domain.Role;

public interface RoleDao {

	@Insert("insert into tbl_role values (#{code}, #{name}, #{description})")
	void save(Role role);
	
	@Delete("delete from tbl_role where code = #{code}")
	void delete(String code);
	
	@Select("select code, name, description from tbl_role where code = #{code}")
	Role getByCode(String code);
	
	@Select("select code, name, description from tbl_role limit #{skipCount}, #{pageSize}")
	List<Role> getAll(Map<String, Integer> page);
	
	@Select("select count(*) from tbl_role")
	int getTotal();
	
	@Select("select count(*) from tbl_role where code = #{code}")
	int checkCode(String code);
	
}

package com.stv.crm.settings.dept.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.stv.crm.settings.dept.domain.Dept;

public interface DeptDao {
	
	@Insert("insert into tbl_dept (id, code, name, manager, phone, description) values (#{id},#{code},#{name},#{manager},#{phone},#{description})")
	void save(Dept dept);
	
	@Delete("delete from tbl_dept where id = #{id}")
	void delete(String id);
	
	@Update("update tbl_dept set code = #{code}, name = #{name}, manager = #{manager}, phone = #{phone}, description = #{description} where id = #{id}")
	void update(Dept dept);
	
	@Select("select id, code, name, manager, phone, description from tbl_dept where id = #{id}")
	Dept getById(String id);
	
	@Select("select id, code, name, manager, phone, description from tbl_dept limit #{skipCount}, #{pageSize}")
	List<Dept> getAll(Map<String, Integer> page);
	
	@Select("select count(*) from tbl_dept")
	int getTotalCount();
	
	@Select("select count(*) from tbl_dept where code = #{code}")
	int checkCode(String code);
	
}

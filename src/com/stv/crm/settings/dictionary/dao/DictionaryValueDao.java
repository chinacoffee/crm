package com.stv.crm.settings.dictionary.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.stv.crm.settings.dictionary.domain.DictionaryValue;

public interface DictionaryValueDao {
	
	@Insert("insert into tbl_dictionary_value (id, value, text, orderNo, typeCode) values (#{id}, #{value}, #{text}, #{orderNo}, #{typeCode})")
	void save(DictionaryValue dictionaryValue);
	
	@Delete("delete from tbl_dictionary_value where id = #{id}")
	void delete(String id);
	
	@Update("update tbl_dictionary_value set value=#{value}, text=#{text}, orderNo=#{orderNo}, typeCode=#{typeCode} where id = #{id}")
	void update(DictionaryValue dictionaryValue);
	
	@Select("select id, value, text, orderNo, typeCode from tbl_dictionary_value where id = #{id}")
	DictionaryValue getById(String id);
	
	@Select("select id, value, text, orderNo, typeCode from tbl_dictionary_value")
	List<DictionaryValue> getAll();
	
	/*
	 * 用注解的方式可以实现
	 * 在去掉注解后,利用xml文件也可以实现
	 * 注解和xml方式可以同时使用
	 * 但是不要存在冲突
	 */
	//@Select("select v.id id, v.value value, v.text text, v.orderNo orderNo, v.typeCode typeCode, t.name typeName from tbl_dictionary_value v, tbl_dictionary_type t where id = #{id} and v.typeCode = t.code")
	Map<String, Object> getByIdWithCodeName(String id);
	
}

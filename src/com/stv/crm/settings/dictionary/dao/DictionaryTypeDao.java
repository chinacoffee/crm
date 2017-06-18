package com.stv.crm.settings.dictionary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.stv.crm.settings.dictionary.domain.DictionaryType;

public interface DictionaryTypeDao {
	
	@Insert("insert into tbl_dictionary_type (code, name, description) values (#{code}, #{name}, #{description})")
	void save(DictionaryType dictionaryType);
	
	@Delete("delete from tbl_dictionary_type where code = #{code}")
	void delete(String code);
	
	@Update("update tbl_dictionary_type set name = #{name}, description = #{description} where code = #{code}")
	void update(DictionaryType dictionaryType);
	
	@Select("select code, name, description from tbl_dictionary_type where code = #{code}")
	DictionaryType getByCode(String code);
	
	@Select("select code, name, description from tbl_dictionary_type")
	List<DictionaryType> getAll();
	
}

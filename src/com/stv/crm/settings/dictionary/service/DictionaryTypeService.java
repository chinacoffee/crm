package com.stv.crm.settings.dictionary.service;

import java.util.List;

import com.stv.crm.settings.dictionary.domain.DictionaryType;

public interface DictionaryTypeService {
	
	void save(DictionaryType dictionaryType);
	
	void delete(String... codes);
	
	void update(DictionaryType dictionaryType);
	
	DictionaryType getByCode(String code);
	
	List<DictionaryType> getAll();
	
}

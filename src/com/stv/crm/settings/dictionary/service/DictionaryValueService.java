package com.stv.crm.settings.dictionary.service;

import java.util.List;
import java.util.Map;

import com.stv.crm.settings.dictionary.domain.DictionaryValue;

public interface DictionaryValueService {
	
	void save(DictionaryValue dictionaryValue);
	
	void delete(String... ids);
	
	void update(DictionaryValue dictionaryValue);
	
	DictionaryValue getById(String id);
	
	List<DictionaryValue> getAll();
	
	Map<String, Object> getByIdWithCodeName(String id);
	
}

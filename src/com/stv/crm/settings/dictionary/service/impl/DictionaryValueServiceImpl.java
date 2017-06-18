package com.stv.crm.settings.dictionary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stv.crm.settings.dictionary.dao.DictionaryValueDao;
import com.stv.crm.settings.dictionary.domain.DictionaryValue;
import com.stv.crm.settings.dictionary.service.DictionaryValueService;
import com.stv.crm.util.SqlSessionUtil;

public class DictionaryValueServiceImpl implements DictionaryValueService{

	@Override
	public void save(DictionaryValue dictionaryValue) {
		DictionaryValueDao dao = SqlSessionUtil.getSession().getMapper(DictionaryValueDao.class);
		dao.save(dictionaryValue);
		
	}

	@Override
	public void delete(String... ids) {
		DictionaryValueDao dao = SqlSessionUtil.getSession().getMapper(DictionaryValueDao.class);
		for (String id : ids) {
			dao.delete(id);
		}
		
	}

	@Override
	public void update(DictionaryValue dictionaryValue) {
		DictionaryValueDao dao = SqlSessionUtil.getSession().getMapper(DictionaryValueDao.class);
		dao.update(dictionaryValue);
	}

	@Override
	public DictionaryValue getById(String id) {
		DictionaryValueDao dao = SqlSessionUtil.getSession().getMapper(DictionaryValueDao.class);
		return dao.getById(id);
	}

	@Override
	public List<DictionaryValue> getAll() {
		DictionaryValueDao dao = SqlSessionUtil.getSession().getMapper(DictionaryValueDao.class);
		return dao.getAll();
	}

	
	/**
	 * select 	v.id id, 
	 * 			v.value value, 
	 * 			v.text text, 
	 * 			v.orderNo orderNo, 
	 * 			v.typeCode typeCode, 
	 * 			t.name typeName 
	 * from tbl_dictionary_value v, tbl_dictionary_type t where id = ? and v.typeCode = t.code
	 */
	@Override
	public Map<String, Object> getByIdWithCodeName(String id) {
		DictionaryValueDao dao = SqlSessionUtil.getSession().getMapper(DictionaryValueDao.class);
		Map<String, Object> data = dao.getByIdWithCodeName(id);
		String value = (String) data.get("value");
		String text = (String) data.get("text");
		int orderNo = (int) data.get("orderNo");
		String typeCode = (String) data.get("typeCode");
		String typeName = (String) data.get("typeName");
		DictionaryValue dictValue = new DictionaryValue();
		dictValue.setId(id);
		dictValue.setValue(value);
		dictValue.setText(text);
		dictValue.setOrderNo(orderNo);
		dictValue.setTypeCode(typeCode);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("typeName", typeName);
		resultMap.put("dictValue", dictValue);
		
		return resultMap;
	}

}

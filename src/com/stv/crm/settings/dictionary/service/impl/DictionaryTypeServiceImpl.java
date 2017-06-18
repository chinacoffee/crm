package com.stv.crm.settings.dictionary.service.impl;

import java.util.List;

import com.stv.crm.settings.dictionary.dao.DictionaryTypeDao;
import com.stv.crm.settings.dictionary.domain.DictionaryType;
import com.stv.crm.settings.dictionary.service.DictionaryTypeService;
import com.stv.crm.util.SqlSessionUtil;

public class DictionaryTypeServiceImpl implements DictionaryTypeService {

	@Override
	public void save(DictionaryType dictionaryType) {
		DictionaryTypeDao dao = SqlSessionUtil.getSession().getMapper(DictionaryTypeDao.class);
		dao.save(dictionaryType);
	}

	@Override
	public void delete(String... codes) {
		DictionaryTypeDao dao = SqlSessionUtil.getSession().getMapper(DictionaryTypeDao.class);
		for (String code : codes) {
			dao.delete(code);
		}

	}

	@Override
	public void update(DictionaryType dictionaryType) {
		DictionaryTypeDao dao = SqlSessionUtil.getSession().getMapper(DictionaryTypeDao.class);
		dao.update(dictionaryType);

	}

	@Override
	public DictionaryType getByCode(String code) {
		DictionaryTypeDao dao = SqlSessionUtil.getSession().getMapper(DictionaryTypeDao.class);
		return dao.getByCode(code);
	}

	@Override
	public List<DictionaryType> getAll() {
		DictionaryTypeDao dao = SqlSessionUtil.getSession().getMapper(DictionaryTypeDao.class);
		return dao.getAll();
	}

}

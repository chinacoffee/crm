package com.stv.crm.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {
	private static SqlSessionFactory sessionFactory;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
	
	static{
		
		String file = "mybatis.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(file);
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SqlSessionUtil(){}
	
	public static SqlSession getSession() {
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession == null) {
			sqlSession = sessionFactory.openSession();
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}
	
	public static void closeSession(SqlSession session) {
		if(session != null){
			session.close();
		}
		threadLocal.remove();
	}
}

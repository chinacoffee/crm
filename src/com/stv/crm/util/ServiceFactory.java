package com.stv.crm.util;

public class ServiceFactory {
	
	private ServiceFactory(){}
	
	public static Object getService(Object service){
		return new TransactionInvocationHandler(service).getProxy();
	}
	
}

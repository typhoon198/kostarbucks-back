package com.day.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.day.dao.OrderDAO;
import com.day.dao.ProductDAO;
import com.day.dto.OrderInfo;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.exception.FindException;

public class OrderService {
	private OrderDAO dao;
	private static OrderService service;
	public static String envProp; //
	private OrderService() {
		Properties env = new Properties();
		try {
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("orderDAO");
			Class c = Class.forName(className);
			dao = (OrderDAO)c.newInstance();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static OrderService getInstance() {
		//return service;
		if(service == null) {
			service = new OrderService();
		}
		return service;
	}
	
	public void add(OrderInfo info) throws AddException{
		dao.insert(info);
	}
	
	public List<OrderInfo> findById(String id) throws FindException{
		return dao.selectById(id);
	}
	
}

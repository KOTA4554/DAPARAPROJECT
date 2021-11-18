package com.kh.dpr.order.model.dao;

import com.kh.dpr.order.model.vo.Order;
import com.kh.dpr.order.model.vo.OrderDetail;

public interface OrderDAO {
	
	int orderInsert(Order order);
	
	Order orderSelectOne();
	
	int orderDetailInsert(OrderDetail orderDetail); 
	
	
	
}

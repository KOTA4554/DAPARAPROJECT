package com.kh.dpr.order.model.service;

import com.kh.dpr.order.model.vo.Order;
import com.kh.dpr.order.model.vo.OrderDetail;

public interface OrderService {
	
	int orderInsert(Order order);
	
	Order orderSelectOne();
	
	int orderDetailInsert(OrderDetail orderDetail); 
	
}

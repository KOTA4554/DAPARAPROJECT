package com.kh.dpr.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.dpr.order.model.service.OrderService;
import com.kh.dpr.order.model.vo.Order;
import com.kh.dpr.order.model.vo.OrderDetail;
import com.kh.dpr.product.model.vo.Product;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping("/order/order.do")
	public String order(//Product[] product,
						//@RequestParam(value="productImg") String[] productImg,
						//@RequestParam(value="detailAmount") int[] detailAmount,
						//@RequestParam(value="detailPrice") int[] detailPrice,
						//@RequestParam(value="detailSize") String[] detailSize,
						//@RequestParam(value="sellerCompany") String[] sellerCompany,
						Model model) {
		
		// 임시데이터
		Product product = new Product();
		
		product.setProductNo(12);
		product.setCategoryNo2(3);
		product.setSizeId(0);
		product.setSellerId("seller01");
		product.setProductId(122);
		product.setProductName("지방시셔츠");
		product.setProductPrice(900000);
		product.setProductAmount(99);
		product.setProductStatus("Y");
		product.setProductInfo("지오지방시");
		product.setProductContent("지오지방시가입은지방시셔츠");
		product.setProductCount(1);
		
		int detailAmount = 1;
		int detailPrice = 300000;
		String detailSize = "L"; 
		String sellerCompany = "GUCCI";
		String productImg = "guccihoodie";
		
		model.addAttribute("product", product);
		model.addAttribute("productImg", productImg);
		model.addAttribute("detailAmount", detailAmount);
		model.addAttribute("detailPrice", detailPrice);
		model.addAttribute("detailSize", detailSize);
		model.addAttribute("sellerCompany", sellerCompany);
		
		return "order/orderSheet";
	}

	
	@RequestMapping("/order/orderByCreditCard.do")
	public String orderByCreditCard(Order order,
									@RequestParam int[] productNo,
									@RequestParam int[] detailAmount,
									@RequestParam int[] detailPrice,
									@RequestParam String[] detailSize, Model model) {
		
		System.out.println("order : " + order);
		
		// order db에 넣기
		int result = orderService.orderInsert(order);
		
		// orderDetail 생성
		OrderDetail orderDetail = null;
		
		String loc = "/order/orderComplete.do";
		String msg = "";
		
		// orderDetail db에 넣기
		if(result > 0) {

			for(int i = 0; i < productNo.length; i++) {
				orderDetail = new OrderDetail();
				
				orderDetail.setProductNo(productNo[i]);
				orderDetail.setDetailAmount(detailAmount[i]);
				//orderDetail.setDetailPrice(productAmount[i] * productId[i].getProductPrice());
				orderDetail.setDetailPrice(detailPrice[i]);
				orderDetail.setDetailSize(detailSize[i]);
				
				System.out.println("주문상세 확인 : " + orderDetail);
				
				int result2 = orderService.orderDetailInsert(orderDetail);
				
				if(result2 > 0) {	
					msg = "결제 완료";
				} else {
					msg = "결제 실패";
				}
			}
			
		} else {
			msg = "결제 실패";
		}
		
		model.addAttribute("loc", loc);
		model.addAttribute("msg", msg);
		
		return "common/msg";
	}
	
	@RequestMapping("/order/orderComplete.do")
	public String orderComplete() {
		return "order/orderComplete";
	}
	
	@RequestMapping("/order/orderByNoBankbook.do")
	public String orderByNoBankBook(Order order,
									@RequestParam int[] productNo,
									@RequestParam int[] productAmount,
									@RequestParam int[] detailPrice, Model model) {
		
		
		return null;
	}
	
}














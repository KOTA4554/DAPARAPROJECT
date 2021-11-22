package com.kh.dpr.product.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dpr.product.model.service.ProductService;
import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.product.model.vo.ProductImage;

@Controller
public class ProductController {
	
	String loc = "/";
	String msg = "";
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("seller/addProduct.do")
	public String addProduct(Model model) {
		// 1. 임시 Productinsert
		int result = productService.insertTempProduct();
		
		// 2. 생성된 가장 최근 Product의 번호( MAX(PRODUCTNO) , CURRVAL )를 반환해서 Model에 담기
		int productNo = productService.selectTempProduct();

		System.out.println(productNo + " productNo 임시 상품 테이블 로드 성공");
		model.addAttribute("productNo", productNo);

		return "productManage/addProduct";
	}
	
	@RequestMapping("seller/optionInsert.do")
	@ResponseBody
	public void optionInsert(@RequestBody List<Product> option) {
		System.out.println(option.size());
		for(Product opt : option) {
			System.out.println(opt);
			int result = productService.insertOption(opt);
			if(result > 0) {
				System.out.println("OptionNo" + opt.getOptionNo() + "등록 성공");
			}
		}
	}
	
	@RequestMapping("seller/productInsert.do")
	public String insertProduct(Product product, Model model, HttpServletRequest req,
								@RequestParam(value="mainImg", required=false) MultipartFile mainImg,
								@RequestParam(value="optionalImg", required=false) MultipartFile[] optionalImgs,
								@RequestParam(value="contentImg", required=false) MultipartFile[] contentImgs) {
		
		System.out.println("product : " + product);
		System.out.println("mainImg : " + mainImg);
		// --- 옵션 정보 받아와서 DB에 등록 -- //
		
		
		int productNo = product.getProductNo();
		System.out.println("product No : " + productNo);
		
		String savePath = req.getServletContext().getRealPath("/resources/productUpload");
		List<ProductImage> imgList = new ArrayList<ProductImage>();
		 
		 
		// 메인이미지 저장 
		if(mainImg.isEmpty() == false) {
			ProductImage mainImage = saveImage(mainImg, 0, productNo, savePath); 
			imgList.add(mainImage);
		}
		
		// 추가이미지 저장 
		for(MultipartFile optionImg : optionalImgs) {
			if(optionImg.isEmpty() == false) {
				ProductImage optionImage = saveImage(optionImg, 1, productNo, savePath);
				imgList.add(optionImage);
			} 
		 }
		 
		// 컨텐츠이미지 저장 
		for(MultipartFile contentImg : contentImgs) {
			if(contentImg.isEmpty() == false) { 
				ProductImage optionImage = saveImage(contentImg, 2, productNo, savePath);
				imgList.add(optionImage); 
			} 
		}
		
		int result = productService.insertProduct(product, imgList);
		if(result > 0) {
			System.out.println("게시글 등록 성공");
		}
		
		return "seller/sellerMain";
	}
	
	public ProductImage saveImage(MultipartFile images, int categroy, int productNo, String savePath) {
		
		String originName = images.getOriginalFilename();
		String changeName = fileRename(productNo, originName);
		
		try {
			images.transferTo(new File(savePath + "/" + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ProductImage img = new ProductImage();
		
		img.setProductNo(productNo);
		img.setImageCategoryNo(categroy);
		img.setProductOldImage(originName);
		img.setProductNewImage(changeName);
		
		return img;
	}

	public String fileRename(int productNo, String oldName) {
		// productNo
		
		String pNo = String.valueOf(productNo);
		String ext = oldName.substring(oldName.lastIndexOf(".") + 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
		int rnd = (int)(Math.random() * 1000);
		
		return pNo + sdf.format(new Date(System.currentTimeMillis())) + "_" + rnd + "." + ext;
	}
	
	@RequestMapping("seller/productList.do")
	public String productList() {
		
		return "productManage/productList";
	}
}

package com.kh.dpr.product.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dpr.common.Utils;
import com.kh.dpr.product.model.service.ProductService;
import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.product.model.vo.ProductImage;
import com.kh.dpr.review.model.vo.Review;
import com.kh.dpr.seller.model.vo.Seller;

@Controller
@SessionAttributes({"seller"})
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
	public String productList(@RequestParam(value="cPage", required=false, defaultValue="1") int cPage,
							  Seller seller, Model model) {
		
		int numPerPage = 15;
		String sellerId = seller.getSellerId();
		
		List<Map<String, String>> list = productService.selectProductList(sellerId, cPage, numPerPage);
		
		int totalProduct = productService.selectTotalProduct(sellerId);
		
		String pageBar = Utils.getPageBar(totalProduct, cPage, numPerPage, "productList.do");
		
		model.addAttribute("list", list);
		model.addAttribute("totalProduct", totalProduct);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("pageBar", pageBar);
		
		return "productManage/productList";
	}
	
	@RequestMapping("seller/searchProd.do")
	public String searchProductList(@RequestParam(value="cPage", required=false, defaultValue="1") int cPage,
									@RequestParam(value="searchNm", required=false) String searchNm,
									@RequestParam(value="searchCate1", required=false) int searchCate1,
									@RequestParam(value="searchCate2", required=false) int searchCate2,
									@RequestParam(value="startDate", required=false) String startDate,
									@RequestParam(value="endDate", required=false) String endDate,
									@RequestParam(value="saleState", required=false) int saleState,
									@RequestParam(value="searchBrand", required=false) String searchBrand,
									@RequestParam(value="searchPno", required=false, defaultValue="-1") int searchPno,
									Seller seller, Model model) {
		
		int numPerPage = 15;
		String sellerId = seller.getSellerId();
		// Date.valueOf(문자열)  : 문자열 형식(2021-11-23 / 20211123)
				
		Map<String, Object> map= new HashMap<>();
		
		System.out.println(startDate);
		
		if( startDate != null && !startDate.equals("")) {
			Date startD = Date.valueOf(startDate);			
			map.put("startDate", startD);
		}
		if( endDate != null && !endDate.equals("")) {
			Date endD = Date.valueOf(endDate);
			map.put("endDate", endD);
			
		}		
		map.put("sellerId", sellerId);
		map.put("searchNm", searchNm);
		map.put("searchCate1", searchCate1);
		map.put("searchCate2", searchCate2);
		map.put("saleState", saleState);
		map.put("searchBrand", searchBrand);
		map.put("searchPno", searchPno);
		
		System.out.println("sellerId : " + map.get("sellerId"));
		System.out.println("searchNm : " + map.get("searchNm"));
		System.out.println("cate1 : " + map.get("searchCate1"));
		System.out.println("cate2 : " + map.get("searchCate2"));
		System.out.println("startDate : " + map.get("startDate"));
		System.out.println("endDate : " + map.get("endDate"));
		System.out.println("brand : " + map.get("searchBrand"));
		System.out.println("state : " + map.get("saleState"));
		System.out.println("p : " + map.get("searchPno"));
		System.out.println("페이지 : " + cPage);
		
		List<Map<String, String>> list = productService.searchProductList(map, cPage, numPerPage);
		
		int totalProduct = productService.selectSearchedProduct(map);
		String pageBar = Utils.getPageBar(totalProduct, cPage, numPerPage, "searchProd.do");
		
		model.addAttribute("list", list);
		model.addAttribute("totalProduct", totalProduct);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("pageBar", pageBar);
		
		return "productManage/productList";
	}
	

	@RequestMapping("/seller/reviewList.do")
	public String reviewList(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		
		Seller s = (Seller)session.getAttribute("seller");
		
		String sellerId = s.getSellerId();
		
		// 판매자가 올린 상품 리뷰 리스트
		List<Review> reviewList = productService.selectReivewList(sellerId);
		
		// 해당 리뷰의 상품
		List<Product> rpList = new ArrayList<Product>();

		for(int i = 0; i < reviewList.size(); i++) {
			
			int reviewNo = reviewList.get(i).getReviewNo();
			
			Product rp = productService.selectRproduct(reviewNo);
			
			rpList.add(rp);
			
		}
		
		int totalProduct = reviewList.size();
		
		model.addAttribute("totalProduct", totalProduct);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("rpList", rpList);
		
		return "productManage/reviewList";
	}
	
}












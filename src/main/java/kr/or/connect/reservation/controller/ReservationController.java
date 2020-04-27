package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@GetMapping(path = "/mainpage")
	public String products(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
			ModelMap model) {
		// 상품 목록 구하기
		List<Product> productList;
		List<Category> categoryList;
		int count = 0;

		productList = reservationService.getAllProduct(start);
		categoryList = reservationService.getCategories();
		for (Category category : categoryList) {
			count += category.getCount();
		}

		List<Promotion> promotionList = reservationService.getPromotions();

		model.addAttribute("productList", productList);
		model.addAttribute("count", count);
		model.addAttribute("promotionList", promotionList);

		return "mainpage";
	}

	@GetMapping(path = "/moreItem")
	@ResponseBody
	public List<Product> getMore(@RequestParam(name = "start", required = true) int start,
			@RequestParam(name = "category_id", required = false, defaultValue = "0") int categoryId) {
		List<Product> productList;
		if (categoryId == 0) {
			productList = reservationService.getAllProduct(start);
		} else {
			productList = reservationService.getProductByCategory(categoryId, start);
		}
		return productList;
	}

	@GetMapping(path = "/category")
	@ResponseBody
	public Map<String, Object> category(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
			@RequestParam(name = "category_id", required = true, defaultValue = "0") int categoryId) {
		List<Product> productList;
		List<Category> categoryList = reservationService.getCategories();
		;
		int totalCount = 0;
		List<Promotion> promotionList;
		if (categoryId == 0) {
			productList = reservationService.getAllProduct(start);
			promotionList = reservationService.getPromotions();
			for (Category category : categoryList) {
				totalCount += category.getCount();
			}
		} else {
			productList = reservationService.getProductByCategory(categoryId, start);
			promotionList = reservationService.getPromotionByCategory(categoryId);
			totalCount = categoryList.get(categoryId - 1).getCount();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		map.put("productList", productList);
		map.put("promotionList", promotionList);

		return map;
	}

}

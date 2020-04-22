package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		int count;

		productList = reservationService.getAllProduct(start);
		count = reservationService.getTotalCount();

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

	@GetMapping(path = "/categoryItem")
	@ResponseBody
	public List<Product> categoryItem(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
			@RequestParam(name = "category_id", required = true, defaultValue = "0") int categoryId) {
		if (categoryId == 0)
			return reservationService.getAllProduct(start);
		else
			return reservationService.getProductByCategory(categoryId, start);
	}

	@GetMapping(path = "/resetCount")
	@ResponseBody
	public Integer resetCount(
			@RequestParam(name = "category_id", required = false, defaultValue = "0") int categoryId) {
		if (categoryId == 0)
			return reservationService.getTotalCount();
		else
			return reservationService.getCountByCategory(categoryId);
	}

	@GetMapping(path = "/promotionItem")
	@ResponseBody
	public List<Promotion> promotionItem(
			@RequestParam(name = "category_id", required = false, defaultValue = "0") int categoryId) {
		if (categoryId == 0)
			return reservationService.getPromotions();
		else
			return reservationService.getPromotionByCategory(categoryId);
	}
}

package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@GetMapping(path = "/mainpage")
	public String products(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId, ModelMap model) {
		// 상품 목록 구하기
		List<Product> productList;
		int count;
		if (categoryId == 0) {
			productList = reservationService.getAllProduct(start);
			count = reservationService.getTotalCount();
		} else {
			productList = reservationService.getProductByCategory(categoryId, start);
			count = reservationService.getCountByCategory(categoryId);
		}

		List<Promotion> promotionList = reservationService.getPromotions();

		model.addAttribute("productList", productList);
		model.addAttribute("count", count);
		model.addAttribute("promotionList", promotionList);

		return "mainpage";
	}

}

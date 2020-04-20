package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;

public interface ReservationService {
	public static final Integer LIMIT = 4;

	public int getTotalCount();

	public int getCountByCategory(Integer categoryId);

	public List<Promotion> getPromotions();

	public List<Product> getAllProduct(Integer start);

	public List<Product> getProductByCategory(Integer categoryId, Integer start);

	public List<Category> getCategories();
}
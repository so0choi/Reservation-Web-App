package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ProductDao productDao;

	@Autowired
	PromotionDao promotionDao;

	@Autowired
	CategoryDao categoryDao;

	@Override
	@Transactional
	public List<Promotion> getPromotions() {
		List<Promotion> promoList = promotionDao.selectPromotionList();
		return promoList;
	}

	@Override
	@Transactional
	public List<Product> getAllProduct(Integer start) {
		List<Product> prdList = productDao.selectAll(start, LIMIT);
		return prdList;
	}

	@Override
	public List<Product> getProductByCategory(Integer categoryId, Integer start) {
		List<Product> prdList = productDao.selectByCategory(start, LIMIT, categoryId);
		return prdList;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> catList = categoryDao.selectCategory();
		return catList;
	}

	@Override
	public List<Promotion> getPromotionByCategory(Integer categoryId) {
		List<Promotion> promotions = promotionDao.selectPromotionByCategory(categoryId);
		return promotions;
	}

}

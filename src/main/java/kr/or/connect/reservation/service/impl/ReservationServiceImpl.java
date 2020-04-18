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
	public int getTotalCount() {
		int count = productDao.selectCountAll();
		return count;
	}

	@Override
	public int getCountByCategory(Integer category_id) {
		int count = productDao.selectCountCategory(category_id);
		return count;
	}

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
	public List<Product> getProductByCategory(Integer category_id, Integer start) {
		List<Product> prdList = productDao.selectByCategory(start, LIMIT, category_id);
		return prdList;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> catList = categoryDao.selectCategory();
		return catList;
	}

}

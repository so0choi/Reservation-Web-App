package kr.or.connect.reservation.testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Promotion;

public class DaoTest {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CategoryDao categoryDao = ac.getBean(CategoryDao.class);

//		List<Category> list = categoryDao.selectCategory();
//		for (Category pd : list) {
//			System.out.println(pd);
//
//		}

//		ProductDao productDao = ac.getBean(ProductDao.class);
//
//		List<Product> plist = productDao.selectByCategory(0, 4, 1);
//		for (Product p : plist) {
//			System.out.println(p);
//
//		}

		PromotionDao promotionDao = ac.getBean(PromotionDao.class);
		List<Promotion> promo = promotionDao.selectPromotionByCategory(1);
		for (Promotion p : promo) {
			System.out.println(p);
		}

	}

}

package kr.or.connect.reservation.testing;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;

public class DaoTest {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CategoryDao category = ac.getBean(CategoryDao.class);
		PromotionDao promotion = ac.getBean(PromotionDao.class);
		ProductDao product = ac.getBean(ProductDao.class);

		List<Category> cl = category.selectCategory();
		System.out.println("category:");
		for (Category c : cl)
			System.out.println(c);
		List<Product> prd = product.selectAll(0, 4);
		System.out.println("product:");
		for (Product p : prd)
			System.out.println(p);
		List<Promotion> prm = promotion.selectPromotionList();
		System.out.println("promotion:");
		for (Promotion m : prm)
			System.out.println(m);

	}

}

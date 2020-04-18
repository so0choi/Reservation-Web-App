package kr.or.connect.reservation.dao;

public class ReservationSqls {
	public static final String SELECT_COUNT_BY_CATEGORY = "select count(*) from product where category_id= :category_id";
	public static final String SELECT_COUNT_ALL = "select count(*)" + " from product";
	public static final String SELECT_PRODUCT_BY_CATEGORY = "select dinfo.id displayInfoId, prd.id, prd.description, prd.content, dinfo.place_name, finfo.save_file_name productImageUrl from product prd, display_info dinfo, product_image img, file_info finfo where prd.id=dinfo.product_id and img.product_id = prd.id and img.type=\"th\" and img.file_id = finfo.id and prd.category_id=:category_id limit :start, :limit";
	public static final String SELECT_ALL_PRODUCT = "select dinfo.id displayInfoId, prd.id, prd.description, dinfo.place_name, prd.content, prd.description, finfo.save_file_name productImageUrl from product prd, display_info dinfo, product_image img, file_info finfo where prd.id=dinfo.product_id and img.product_id = prd.id and img.type=\"th\" and img.file_id = finfo.id limit :start, :limit ";

	public static final String SELECT_CATEGORY = "select category.id, category.name, count(*) count from category, product where category.id = product.category_id group by category_id; ";

	public static final String SELECT_PROMOTION = "select promotion.id, product.id productId, file_info.save_file_name productImageUrl from promotion, product, product_image, file_info where promotion.product_id = product.id and product.id = product_image.product_id and product_image.file_id = file_info.id and product_image.type=\"th\";";

}

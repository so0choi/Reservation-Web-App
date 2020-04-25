package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationSqls.SELECT_ALL_PRODUCT;
import static kr.or.connect.reservation.dao.ReservationSqls.SELECT_PRODUCT_BY_CATEGORY;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectAll(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_PRODUCT, params, rowMapper);
	}

	public List<Product> selectByCategory(Integer start, Integer limit, Integer categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("category_id", categoryId);
		return jdbc.query(SELECT_PRODUCT_BY_CATEGORY, params, rowMapper);
	}
}

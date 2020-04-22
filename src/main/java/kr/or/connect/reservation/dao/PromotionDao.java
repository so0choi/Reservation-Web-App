package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationSqls.SELECT_PROMOTION;
import static kr.or.connect.reservation.dao.ReservationSqls.SELECT_PROMOTION_BY_CATEGORY;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);

	}

	public List<Promotion> selectPromotionList() {

		return jdbc.query(SELECT_PROMOTION, rowMapper);
	}

	public List<Promotion> selectPromotionByCategory(Integer categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category_id", categoryId);
		return jdbc.query(SELECT_PROMOTION_BY_CATEGORY, params, rowMapper);
	}
}

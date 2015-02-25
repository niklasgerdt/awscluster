package aws.cluster.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import aws.cluster.order.entity.OrderEntity;

@Repository
public class OrderRepository {
    private final static Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private final NamedParameterJdbcTemplate jdbc;
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderRepository(final DataSource dataSource, final SessionFactory sessionFactory) {
        logger.info("created OrderRepository");
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.sessionFactory = sessionFactory;
    }

    public List<OrderEntity> getOrdersByUserId(final int userId) {
        logger.info("fetching orders for user {}", userId);
        SqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId);
        String sql = "select * from APP.order where userid = :userId";
        List<Map<String, Object>> orders = jdbc.queryForList(sql, params);
        List<OrderEntity> orderList = new ArrayList<>();
        orders.stream().forEach(m -> orderList.add(new OrderEntity((Integer) m.get("id"), (Integer) m.get("userid"), (String) m.get("description"))));
        logger.info("fetched orders {} for user {}", orderList, userId);
        return orderList;
    }
}

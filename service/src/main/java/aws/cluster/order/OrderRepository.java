package aws.cluster.order;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import aws.cluster.order.entity.Order;

@Repository
public class OrderRepository {
    private final static Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderRepository(final SessionFactory sessionFactory) {
        logger.info("created OrderRepository");
        this.sessionFactory = sessionFactory;
    }

    public List<Order> getOrdersByUserId(final int userId) {
        logger.info("fetching orders for user {}", userId);
        Session s = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Order> orders = s.createQuery("from Order where userid = :userid").setParameter("userid", userId).list();
        logger.info("fetched orders {} for user {}", orders, userId);
        return orders;
    }

    public Order getOrder(Integer customerId, Integer orderId) {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.createQuery("from Order where userid = :userid and id = :orderid");
        q.setParameter("userid", customerId);
        q.setParameter("orderid", orderId);
        Order o = (Order) q.list().get(0);
        return o;
    }

    public Order newOrder(Integer customerId, String description) {
        Order o = new Order();
        o.setCustomerId(customerId);
        o.setDescription(description);
        Session s = sessionFactory.getCurrentSession();
        s.save(o);
        return o;
    }

    public Order updateOrder(Integer orderId, Integer customerId, String description) {
        Order o = getOrder(customerId, orderId);
        o.setDescription(description);
        Session s = sessionFactory.getCurrentSession();
        s.save(o);
        return o;
    }
}

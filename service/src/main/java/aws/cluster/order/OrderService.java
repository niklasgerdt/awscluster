package aws.cluster.order;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import aws.cluster.order.entity.Order;

@Service
public class OrderService {
    private final static Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByUserId(Integer customerId) {
        logger.info("serving orders for customer {}", customerId);
        return orderRepository.getOrdersByUserId(customerId);
    }

    @Transactional(readOnly = true)
    public Order getOrder(Integer customerId, Integer orderId) {
        logger.info("serving order {} for customer {}", orderId, customerId);
        return orderRepository.getOrder(customerId, orderId);
    }

    @Transactional(readOnly = false)
    public Order newOrder(Integer customerId, String description) {
        logger.info("creating new order {} for customer {}", description, customerId);
        return orderRepository.newOrder(customerId, description);
    }

    @Transactional(readOnly = false)
    public Order updateOrder(Integer customerId, Integer orderId, String description) {
        logger.info("updating order {} for customer {}", orderId, customerId);
        return orderRepository.updateOrder(orderId, customerId, description);
    }
}

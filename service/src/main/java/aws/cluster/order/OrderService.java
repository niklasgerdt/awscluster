package aws.cluster.order;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderService {
    private final static Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public List<OrderEntity> getOrders(@PathVariable Integer customerId) {
        logger.info("fetching orders for customer {}", customerId);
        return orderRepository.getOrdersByUserId(customerId);
    }

    @RequestMapping(value = "/{customerId}/{orderId}", method = RequestMethod.GET)
    public OrderEntity getOrder(@PathVariable Integer customerId, @PathVariable Integer orderId) {
        logger.info("fetching order {} for customer {}", orderId, customerId);
        OrderEntity order = new OrderEntity(orderId, customerId, "desc.");
        return order;
    }

    @RequestMapping(value = "/{customerId}/{orderId}", method = RequestMethod.PUT)
    public String insertOrder(@PathVariable Integer customerId, @PathVariable Integer orderId) {
        logger.info("creating new order {} for customer {}", orderId, customerId);
        return "NADA" + customerId;
    }

    @RequestMapping(value = "/{customerId}/{orderId}", method = RequestMethod.POST)
    public String updateOrder(@PathVariable Integer customerId, @PathVariable Integer orderId) {
        logger.info("updating order {} for customer {}", orderId, customerId);
        return "NADA" + customerId;
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.POST)
    public String insertOrder(@PathVariable Integer customerId) {
        logger.info("creating new order for customer {}", customerId);
        return "NADA" + customerId;
    }
}

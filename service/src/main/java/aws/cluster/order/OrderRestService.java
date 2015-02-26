package aws.cluster.order;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import aws.cluster.order.entity.Order;

@RestController
@RequestMapping("/orders")
public class OrderRestService {
    private final static Logger logger = LoggerFactory.getLogger(OrderRestService.class);
    private final OrderService orderService;

    @Autowired
    public OrderRestService(final OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public List<Order> getOrders(@PathVariable Integer customerId) {
        logger.info("fetching orders for customer {}", customerId);
        return orderService.getOrdersByUserId(customerId);
    }

    @RequestMapping(value = "/{customerId}/{orderId}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable Integer customerId, @PathVariable Integer orderId) {
        logger.info("fetching order {} for customer {}", orderId, customerId);
        Order order = new Order(orderId, customerId, "desc.");
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

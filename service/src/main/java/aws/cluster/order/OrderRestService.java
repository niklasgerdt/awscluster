package aws.cluster.order;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public List<OrderData> getOrders(@PathVariable Integer customerId, @RequestHeader("Host") String host) {
        logger.info("fetching orders for customer {}", customerId);
        List<Order> orders = orderService.getOrdersByUserId(customerId);
        List<OrderData> rets = new ArrayList<>();
        orders.stream().forEach(o -> rets.add(new OrderData(o.getDescription(), buildLink(host, customerId, o))));
        return rets;
    }

    @RequestMapping(value = "/{customerId}/{orderId}", method = RequestMethod.GET, produces = "application/json")
    public OrderData getOrder(@PathVariable Integer customerId, @PathVariable Integer orderId,
            @RequestHeader("Host") String host) {
        logger.info("fetching order {} for customer {}", orderId, customerId);
        Order order = orderService.getOrder(customerId, orderId);
        return new OrderData(order.getDescription(), buildLink(host, customerId, order));
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public OrderData insertOrder(@PathVariable Integer customerId, @RequestBody OrderData data,
            @RequestHeader("Host") String host) {
        logger.info("creating new order {} for customer {}", data, customerId);
        Order o = orderService.newOrder(customerId, data.getDescription());
        return new OrderData(o.getDescription(), buildLink(host, customerId, o));
    }

    @RequestMapping(value = "/{customerId}/{orderId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public OrderData updateOrder(@PathVariable Integer customerId, @PathVariable Integer orderId,
            @RequestBody OrderData data, @RequestHeader("Host") String host) {
        logger.info("updating order {} for customer {}", orderId, customerId);
        Order o = orderService.updateOrder(customerId, orderId, data.getDescription());
        return new OrderData(o.getDescription(), buildLink(host, customerId, o));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleMyException(Exception exception) {
        logger.error(exception.getMessage());
        return "INTERNAL ERROR";
    }

    private String buildLink(String host, Integer customerId, Order o) {
        return host + "/service/orders/" + customerId + "/" + o.getId();
    }
}

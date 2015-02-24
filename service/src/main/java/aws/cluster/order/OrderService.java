package aws.cluster.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService {
    private final static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @RequestMapping("/{custemerId}")
    public String getOrders(@RequestParam(value = "customerId") String customerId) {
        logger.info("fetching orders for customer {}", customerId);
        return "NADA";
    }
}

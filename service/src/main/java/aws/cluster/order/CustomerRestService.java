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
import aws.cluster.order.entity.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerRestService {
    private final static Logger logger = LoggerFactory.getLogger(CustomerRestService.class);
    private final CustomerService customerService;

    @Autowired
    public CustomerRestService(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<CustomerData> getCustomers(@RequestHeader("Host") String host) {
        logger.info("fetching customer");
        List<Customer> customers = customerService.getUsers();
        List<CustomerData> rets = new ArrayList<>();
        customers.stream().forEach(c -> rets.add(new CustomerData(c.getName(), buildLink(host, c))));
        return rets;
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public CustomerData getCustomer(@PathVariable Integer customerId, @RequestHeader("Host") String host) {
        logger.info("fetching customer {} data", customerId);
        Customer cust = customerService.getUser(customerId);
        return new CustomerData(cust.getName(), buildLink(host, cust));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public CustomerData insertCustomer(@RequestBody CustomerData data, @RequestHeader("Host") String host) {
        logger.info("creating new customer: {} ", data);
        Customer user = customerService.newUser(data.getName());
        CustomerData ret = new CustomerData();
        ret.setName(user.getName());
        ret.setLink(buildLink(host, user));
        return ret;
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public CustomerData updateCustomer(@PathVariable Integer customerId, @RequestBody CustomerData data,
            @RequestHeader("Host") String host) {
        logger.info("updating customer {} ({})", customerId, data.getName());
        Customer user = customerService.updateUser(customerId, data.getName());
        CustomerData ret = new CustomerData(user.getName(), buildLink(host, user));
        return ret;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleMyException(Exception exception) {
        logger.error(exception.getMessage());
        return "INTERNAL ERROR";
    }

    private String buildLink(String host, Customer c) {
        return host + "/service/customers/" + c.getId();
    }
}

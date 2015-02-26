package aws.cluster.order;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import aws.cluster.order.entity.Customer;

@Service
public class CustomerService {
    private final static Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> getUsers() {
        logger.info("serving users");
        return customerRepository.getUsers();
    }

    @Transactional(readOnly = true)
    public Customer getUser(Integer customerId) {
        logger.info("serving user {}", customerId);
        return customerRepository.getUser(customerId);
    }

    @Transactional()
    public Customer newUser(String name) {
        logger.info("creating new user: {}", name);
        return customerRepository.newUser(name);
    }

    @Transactional()
    public Customer updateUser(Integer customerId, String name) {
        logger.info("updating user {} ({})", customerId, name);
        return customerRepository.updateUser(customerId, name);
    }
}

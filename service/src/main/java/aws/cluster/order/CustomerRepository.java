package aws.cluster.order;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import aws.cluster.order.entity.Customer;

@Repository
public class CustomerRepository {
    private final static Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerRepository(final SessionFactory sessionFactory) {
        logger.info("created CustomerRepository");
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Customer> getUsers() {
        Session s = sessionFactory.getCurrentSession();
        return s.createCriteria(Customer.class).list();
    }

    public Customer newUser(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        Session s = sessionFactory.getCurrentSession();
        s.save(customer);
        return customer;
    }

    public Customer getUser(Integer customerId) {
        Session s = sessionFactory.getCurrentSession();
        return (Customer) s.get(Customer.class, customerId);
    }
}

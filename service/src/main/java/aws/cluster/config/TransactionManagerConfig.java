package aws.cluster.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {
    private final static Logger logger = LoggerFactory.getLogger(TransactionManagerConfig.class);
    @Autowired
    private DataSource datasource;

    @Bean
    public PlatformTransactionManager txManager() {
        logger.info("setting up transaction manager");
        return new DataSourceTransactionManager(datasource);
    }
}

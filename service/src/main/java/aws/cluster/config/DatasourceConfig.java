package aws.cluster.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:/data.properties")
public class DatasourceConfig {
    private final static Logger logger = LoggerFactory.getLogger(DatasourceConfig.class);
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource datasource() {
        logger.info("creating datasource (driver: {}, url: {}, user: {} password: {})", driver, url, userName, password);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        logger.info("created datasource (driver: {}, url: {}, user: {} password: {})", driver, url, userName, password);
        return dataSource;
    }
}

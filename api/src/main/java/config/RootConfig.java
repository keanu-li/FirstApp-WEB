package config;

import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@ComponentScan(basePackages = { "me.firstapp.module", "me.firstapp.repository", "me.firstapp.service" })
public class RootConfig {

	// ==============================数据源配置==================================
	@Bean
	public DruidDataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		// 基本属性 url、user、password
		druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/firstapp?useUnicode=true" + "&characterEncoding=utf-8"
				+ "&autoReconnect=true" + "&connectTimeout=0" + "&socketTimeout=0"
				+ "&zeroDateTimeBehavior=convertToNull");
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("123qweasd");
		// 配置初始化大小、最小、最大
		druidDataSource.setInitialSize(1);
		druidDataSource.setMinIdle(1);
		druidDataSource.setMaxActive(20);
		// 配置获取连接等待超时的时间
		druidDataSource.setMaxWait(60000);
		// 配置一个连接在池中最小生存的时间，单位是毫秒
		druidDataSource.setMinEvictableIdleTimeMillis(300000);
		druidDataSource.setValidationQuery("SELECT 'x'");
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);

		// 打开PSCache，并且指定每个连接上PSCache的大小
		druidDataSource.setPoolPreparedStatements(true);
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

		// 配置监控统计拦截的filters
		try {
			druidDataSource.setFilters("stat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}

	// ============================hibernate配置=====================================
	@Bean
	public LocalSessionFactoryBean sessionFactory(DruidDataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.hbm2ddl.auto", "update");
		sessionFactory.setHibernateProperties(properties);
		sessionFactory.setPackagesToScan("me.firstapp.module");
		return sessionFactory;
	}

	@Bean
	public HibernateTemplate hibernateTemplate(LocalSessionFactoryBean sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate();
		hibernateTemplate.setSessionFactory(sessionFactory.getObject());
		return hibernateTemplate;
	}

	// =================================事务配置==================================================
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	@Bean
	public TransactionTemplate transactionTemplate(HibernateTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager);
		return transactionTemplate;
	}
}

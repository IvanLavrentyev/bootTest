//package com.example.demo.datasource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource("classpath:db.properties")
//@EnableJpaRepositories(basePackages = "com")
//@ComponentScan(basePackages = "com")
//public class DataSourceConfig {
//    private Environment environment;
//
//    @Autowired
//    public DataSourceConfig(Environment environment) {
//        this.environment = environment;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(){
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return jpaTransactionManager;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(getDataSource());
//        factoryBean.setJpaVendorAdapter(getAdapter());
//        factoryBean.setJpaProperties(getHibernateProperties());
//        factoryBean.setPackagesToScan("com");
//        return factoryBean;
//    }
//
//    @Bean
//    public DataSource getDataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("driver"));
//        dataSource.setUrl(environment.getRequiredProperty("url"));
//        dataSource.setUsername(environment.getRequiredProperty("u_name"));
//        dataSource.setPassword(environment.getRequiredProperty("u_password"));
//        return dataSource;
//    }
//
//    @Bean
//    public JpaVendorAdapter getAdapter(){
//        return new HibernateJpaVendorAdapter();
//    }
//
//    private Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
//        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        return properties;
//    }
//}

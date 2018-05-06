package com.df.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:dao/pro.properties")
public class DataSourceConfiguration {

        @Value("${jdbc.driver}")
        private String driver;

        @Value("${jdbc.url}")
        private String url;

        @Value("${jdbc.username}")
        private String username;

        @Value("${jdbc.password}")
        private String password;

        @Value("${jdbc.maxActive}")
        private Integer maxActive;

        @Value("${jdbc.maxIdel}")
        private Integer maxIdel;

        @Value("${jdbc.maxWait}")
        private Integer maxWait;


        @Bean
        public DruidDataSource dataSource(){
                DruidDataSource dataSource=new DruidDataSource();
                dataSource.setDriverClassName(driver);
                dataSource.setUrl(url);
                dataSource.setUsername(username);

                dataSource.setPassword(password);
                dataSource.setMaxActive(maxActive);
              /*  dataSource.setMaxWait(maxWait);*/

                return  dataSource;
        }
}

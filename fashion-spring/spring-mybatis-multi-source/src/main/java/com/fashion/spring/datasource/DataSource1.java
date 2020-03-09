package com.fashion.spring.datasource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.fashion.spring.mapper.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DataSource1 {

    @Value("${mybatis.db1-mapper-locations}")
    private String db1MapperLocations;

    @Value("${mybatis.db1-aliases-package}")
    private String db1AliasesPackage;


    @Primary
    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    //然后创建 SqlSessionFactory
    @Primary
    @Bean(name = "db1SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        bean.setTypeAliasesPackage(db1AliasesPackage);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(db1MapperLocations));
        return bean.getObject();
    }

    //再创建事务
    @Primary
    @Bean(name = "db1TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //最后包装到 SqlSessionTemplate 中
    @Primary
    @Bean(name = "db1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

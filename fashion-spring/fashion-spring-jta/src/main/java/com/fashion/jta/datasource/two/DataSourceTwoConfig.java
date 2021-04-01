package com.fashion.jta.datasource.two;

import com.fashion.jta.config.one.DruidOneParam;
import com.fashion.jta.config.two.DruidTwoParam;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Author: yangyuguang
 * @Date: 2021/4/1 10:27
 */
@Configuration
@MapperScan(basePackages = {"com.fashion.jta.mapper.two"},
        sqlSessionTemplateRef ="data02SqlSessionTemplate")
public class DataSourceTwoConfig {

    @Autowired
    private DruidTwoParam druidOneParam;

    @Bean("dataSourceTwo")
    public DataSource dataSourceOne(){
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(druidOneParam.getDbUrl());
        mysqlXADataSource.setUser(druidOneParam.getUsername());
        mysqlXADataSource.setPassword(druidOneParam.getPassword());
        try {
            mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 事务管理器
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("dataSourceTwo");
        return atomikosDataSourceBean;
    }

    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryOne(
            @Qualifier("dataSourceTwo") DataSource dataSourceOne) throws Exception{
        // 配置Session工厂
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceOne);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:/dataTwoMapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "data02SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactory) {
        // 配置Session模板
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

package com.elitel.datasource.config;

import com.elitel.datasource.mybatis.annotation.MapperScanner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScanner(basePackages = {"${el.mapper.package}"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class ElitelMasterDataSourceConfiguration {

    @Value("${el.mybatis.mapper-locations}")
    private String mapper_location = "";

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource") // application.properteis中对应属性的前缀
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        if(mapper_location == null || mapper_location == "" || "".equals(mapper_location)) {
            throw new IllegalArgumentException("property ${el.mybatis.mapper-locations} can not find!!!");
        }

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String[] mapper_locations = mapper_location.split(",");
        List<Resource> resourceList = new ArrayList<Resource>();
        for(String url: mapper_locations){
            Resource[] resources = resolver.getResources(url);
            for(int i = 0; i < resources.length; i++) {
                resourceList.add(resources[i]);
            }
        }
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(resourceList.toArray(new Resource[resourceList.size()]));
        return sessionFactory.getObject();
    }

}

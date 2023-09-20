package com.study.springbatch.core.db.construct;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.study.springbatch.core.db.annotation.PrimaryConnection;
import com.study.springbatch.core.db.annotation.SecondaryConnection;

class MyBatisConfig {
    public static final String BASE_PACKAGE = "com.study";
}

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = MyBatisConfig.BASE_PACKAGE, annotationClass = PrimaryConnection.class, sqlSessionFactoryRef = "primarySqlSessionFactory")
class PrimaryMyBatisConfig {
    @Primary
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception {
        return SqlSessionFactoryBuilder.build(primaryDataSource);
    }

    @Primary
    @Bean(name = "primaryDataSource", destroyMethod = "")
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(primaryDataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
}

@Configuration
@MapperScan(basePackages = MyBatisConfig.BASE_PACKAGE, annotationClass = SecondaryConnection.class, sqlSessionFactoryRef = "secondarySqlSessionFactory")
class SecondaryMyBatisConfig {
    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) throws Exception {
        return SqlSessionFactoryBuilder.build(secondaryDataSource);
    }

    @Bean(name = "secondaryDataSource", destroyMethod = "")
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}

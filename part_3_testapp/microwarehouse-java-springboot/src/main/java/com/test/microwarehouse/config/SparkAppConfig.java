package com.test.microwarehouse.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkAppConfig {

    @Value("${spark.app.name}")
    private String sparkAppName;

    @Value("${spark.master.host}")
    private String sparkMasterHost;

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName(sparkAppName)
                .master(sparkMasterHost)
                .getOrCreate();
    }
}

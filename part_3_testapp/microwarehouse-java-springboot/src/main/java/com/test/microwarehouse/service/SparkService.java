package com.test.microwarehouse.service;

import com.test.microwarehouse.dto.TopCuisineDto;
import org.apache.spark.sql.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import static org.apache.spark.sql.functions.countDistinct;
import static org.apache.spark.sql.functions.desc;

@Service
public class SparkService {

    private static final String GROUP_TYPE = "cuisine_type";
    private static final String AGG_COLUMN = "restaurant_name";
    private static final String ORDER_COLUMN = "count";

    @Value("${dataset.file.path}")
    private String datasetFilePath;

    private final SparkSession sparkSession;

    public SparkService(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public List<TopCuisineDto> runSparkJob() {
        // we run and wait for the result, alternative we can run this job with scheduler
        // and save the result to the file in HDFS, after that to call another method to just read this result file
        Dataset<Row> data = sparkSession.read()
                .option("header","true")
                .csv(datasetFilePath);

        Dataset<Row> cuisineType = data.groupBy(GROUP_TYPE)
                .agg(countDistinct(AGG_COLUMN).as(ORDER_COLUMN))
                .orderBy(desc(ORDER_COLUMN)).cache();;

        return cuisineType.as(Encoders.bean(TopCuisineDto.class))
                .collectAsList();
    }
}

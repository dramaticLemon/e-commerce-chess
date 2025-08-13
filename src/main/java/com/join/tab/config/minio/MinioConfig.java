package com.join.tab.config.minio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@PropertySource("classpath:application.properties")
public class MinioConfig{

	private static final Logger log = LoggerFactory.getLogger(MinioConfig.class);

	@Value("${minio.endpoint}")
	private String endpoint;

	@Value("${minio.region}")
	private String region;

	@Value("${minio.access-key}")
	private String accessKey;

	@Value("${minio.secret-key}")
	private String secretKey;
	
	@Bean
	public AmazonS3 minioClient() {

		log.info(region);
		log.info(endpoint);
		log.info(accessKey);
		log.info(secretKey);

        return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(new EndpointConfiguration(endpoint, region))
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))
			)
            .withPathStyleAccessEnabled(true)
            .build();
    }	
}

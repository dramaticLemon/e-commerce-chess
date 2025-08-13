package com.join.tab.services.admin;

import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;




@Service
public class MinioService{
	
	private final AmazonS3 minioClient;
	private final String bucketName = "products-bucket";
	
	public MinioService(AmazonS3 minioClient) {
		this.minioClient = minioClient;

		if (!minioClient.doesBucketExistV2(bucketName)) {
			minioClient.createBucket(bucketName);
		}
	}

	public String uploadFile(InputStream inputStream, String fileName) {
		minioClient.putObject(
			new PutObjectRequest(
				bucketName,
				fileName,
				inputStream,
				null)
			);
		return bucketName + "/" + fileName;
	}


	 public Resource loadFileAsResource(String fullPath) {
    // Пример: fullPath = "products-bucket/chess-banner.jpg"
		String[] parts = fullPath.split("/", 2);
		String bucket = parts[0];
		String key = parts.length > 1 ? parts[1] : "";

		S3Object s3Object = minioClient.getObject(new GetObjectRequest(bucket, key));
		return new InputStreamResource(s3Object.getObjectContent());
	}

	
}

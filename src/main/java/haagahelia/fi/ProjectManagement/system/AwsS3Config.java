package haagahelia.fi.ProjectManagement.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/*
 * File upload / download handled by AWS S3 storage 
 * Reference: https://devkonline.com/tutorials/content/spring-aws-s3
 * 
 */


@Configuration
public class AwsS3Config {

	@Value("${aws.accessKey}")
	private String awsAccessKey;

	@Value("${aws.secretKey}")
	private String awsSecretKey;

	@Value("${aws.bucketName}")
	private String awsABucketName;

	@Value("${aws.region}")
	private String region;

	@Bean
	public AmazonS3 amazonS3Client() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

		return s3Client;
	}
}

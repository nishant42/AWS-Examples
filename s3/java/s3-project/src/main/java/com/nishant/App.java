package com.nishant;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        String bucketName = "nishant-test-bucket-" + System.currentTimeMillis();

        // CHANGE THIS: Set your region (e.g., EU_WEST_2 for London)
        Region region = Region.EU_WEST_2;

        System.out.println("Attempting to create bucket: " + bucketName);

        try (S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build()) {

            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();

            s3.createBucket(createBucketRequest);

            System.out.println("Success! Bucket created.");

        } catch (S3Exception e) {
            System.err.println("AWS Error: " + e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

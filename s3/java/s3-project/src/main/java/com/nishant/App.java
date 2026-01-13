package com.nishant;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody; // <--- Missing
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest; // <--- Missing
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // 1. Setup
        String bucketName = "nishant-demo-" + System.currentTimeMillis();
        String keyName = "my-first-upload.txt"; // The name it will have in S3
        Region region = Region.EU_WEST_2;

        try (S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build()) {

            // 2. Create the Bucket
            System.out.println("Creating bucket: " + bucketName);
            s3.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
            System.out.println("Bucket created.");
            
            // 3. Create a dummy file on your Mac to upload
            // This creates a file named "local-test.txt" in your project folder
            Path localFile = Paths.get("local-test.txt");
            Files.write(localFile, "Hello! This is content inside the file.".getBytes());

            // 4. Upload the file
            System.out.println("Uploading file...");

            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .build();

            // This is the core line for uploading
            s3.putObject(putOb, RequestBody.fromFile(localFile));

            System.out.println("Success! File uploaded as: " + keyName);

        } catch (S3Exception e) {
            System.err.println("AWS Error: " + e.awsErrorDetails().errorMessage());
        } catch (IOException e) {
            System.err.println("File Error: " + e.getMessage());
        }
    }
}

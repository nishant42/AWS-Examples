package com.myorg;

import software.constructs.Construct;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.SqsSubscription;
import software.amazon.awscdk.services.sqs.Queue;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketEncryption;
import software.amazon.awscdk.RemovalPolicy;

public class CdkStack extends Stack {
    public CdkStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CdkStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        // final Queue queue = Queue.Builder.create(this, "CdkQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();

        // final Topic topic = Topic.Builder.create(this, "CdkTopic")
        //         .displayName("My First Topic Yeah")
        //         .build();

        // topic.addSubscription(new SqsSubscription(queue));

        // 3. Define the S3 Bucket
        final Bucket bucket = Bucket.Builder.create(this, "MyCdkBucket")
                .versioned(true)
                // Optional: S3_MANAGED is default, but you can be explicit
                .encryption(BucketEncryption.S3_MANAGED)
                // Useful for dev: deletes the bucket even if it contains files
                .removalPolicy(RemovalPolicy.DESTROY)
                .autoDeleteObjects(true)
                .build();
    }
}

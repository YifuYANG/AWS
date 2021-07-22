package app.Config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.credentials.session-token}")
    private String token;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.end-point.url}")
    private String DynamodbEndPoint;

    @Bean
    public DynamoDBMapper mapper(){
        return new DynamoDBMapper(amazonDynamoDBConfig());
    }

    public AmazonDynamoDB amazonDynamoDBConfig(){
        return AmazonDynamoDBClientBuilder.standard().
                withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DynamodbEndPoint,region)).
                withCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials(accessKey,secretKey,token))).
                build();
    }
}

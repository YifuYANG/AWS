package app.JpaRepository;

import app.Entities.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class UserRepositoryDydb {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User findByName(String name){
        return dynamoDBMapper.load(User.class,name);
    }

    public void save(User user){
        dynamoDBMapper.save(user);
    }
}

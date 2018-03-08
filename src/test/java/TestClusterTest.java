import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/spring.xml"})
public class TestClusterTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testClusterTest(){

        redisTemplate.execute(new RedisCallback<Integer>() {
            //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
            public Integer doInRedis(RedisConnection connection) {
                int i = 0;
                for (; i < 100; i++) {
                    byte[] key = ("key:" + i).getBytes();
                    byte[] value = ("value:" + i).getBytes();
//                    connection.get(key, value);
//                    System.out.println(connection.get(value));
                }
                //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
                System.out.println(stringRedisTemplate.opsForValue().get("key:0"));
                return i;

            }
        });
    }
}

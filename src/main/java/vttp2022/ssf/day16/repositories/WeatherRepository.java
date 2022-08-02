package vttp2022.ssf.day16.repositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

    @Value("${weather.cache.duration}")
    private Long cacheTime;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void save(String city, String payLoad) {
        ValueOperations<String, Object> valueOp = redisTemplate.opsForValue();
        valueOp.set(city.toLowerCase(), payLoad, Duration.ofMinutes(cacheTime));
    }

    public Optional<String> get(String city) {
        ValueOperations<String, Object> valueOp = redisTemplate.opsForValue();
        String value = (String) (valueOp.get(city));
        if (null == value) {
            return Optional.empty(); // empty box
        }
        return Optional.of(value); // box with data
    }

}

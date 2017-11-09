package ch.sbb.apim.amssp.controller;

import ch.sbb.apim.amssp.model.StringKeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

/**
 * TODO: Describe
 *
 * @author Christian Egli
 * @since 11/8/17.
 */
@RestController
public class RedisController {

    private StringRedisTemplate template;

    @Autowired
    public RedisController(StringRedisTemplate template) {
        this.template = template;
    }

    // cURL:
    // echo '{"key":"key3","value":"value3"}' | curl -H "Content-Type: application/json" -d @- http://localhost:8080/singlekey
    //
    @PostMapping("/singlekey")
    public String getAllKeys(@RequestBody StringKeyValue keyValue) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set(keyValue.getKey(), keyValue.getValue());
        return "Key '" + keyValue.getKey() + "' saved";
    }

    // cURL:
    // curl localhost:8080/singlekey/key2
    //
    @GetMapping("/singlekey/{key}")
    public StringKeyValue getKeyValue(@PathVariable String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        return new StringKeyValue(key, ops.get(key));
    }


}

package mockclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by Caozheng on 2018/5/17.
 */
@RestController
public class PingController {

    private String id = UUID.randomUUID().toString();

    @GetMapping("/ping")
    public String ping(){

        return id;
    }
}


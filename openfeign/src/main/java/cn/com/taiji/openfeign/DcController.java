package cn.com.taiji.openfeign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {
    private static Logger log = LoggerFactory.getLogger(DcController.class);

    @Autowired
    private DcClientService dcClientService;

    @GetMapping("/dc")
    public String dc() throws InterruptedException {
        String result = dcClientService.consumer();
        //此处进行日志追踪
        log.info(result);
        return result;
    }
}

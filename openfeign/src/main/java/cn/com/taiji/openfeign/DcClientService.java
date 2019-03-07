package cn.com.taiji.openfeign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "resource", fallbackFactory = DcClientServiceFallbackFactory.class)
public interface DcClientService {
    @GetMapping("/dc")
    String consumer();
}

@Component
class DcClientServiceFallbackFactory implements FallbackFactory<DcClientService> {
    private static final Logger logger = LoggerFactory.getLogger(DcClientServiceFallbackFactory.class);

    public DcClientService create(final Throwable throwable) {
        return new DcClientService() {

            @Override
            public String consumer() {
                logger.error("Fallback reason = {}", throwable.getMessage());
                // 走异常逻辑，不建议直接返回值
                // throw new RuntimeException(throwable.getCause());
                return "服务器开小差啦";
            }
        };
    }
}

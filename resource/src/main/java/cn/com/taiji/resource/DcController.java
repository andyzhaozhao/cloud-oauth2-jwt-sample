package cn.com.taiji.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {
	private static Logger log=LoggerFactory.getLogger(DcController.class);

	@Autowired
    DiscoveryClient discoveryClient;

	@GetMapping("/dc")
	public String dc() throws InterruptedException {
	    String services = "Services: " + discoveryClient.getServices();
        log.info(services);
		return services;
	}
}

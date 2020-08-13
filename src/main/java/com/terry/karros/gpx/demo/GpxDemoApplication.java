package com.terry.karros.gpx.demo;

import com.terry.karros.gpx.demo.aspect.LoggingAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.terry.karros.gpx.demo"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GpxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpxDemoApplication.class, args);
	}

}

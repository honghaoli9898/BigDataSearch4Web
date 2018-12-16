package com.tl.job002.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tl.job002.threads.IndexDataSwitchThread;

@SpringBootApplication
@ComponentScan(basePackages="com.tl")
public class SystemController {
	public static void main(String[] args) {
		SpringApplication.run(SystemController.class, args);
		IndexDataSwitchThread.init();
	}
}

package com.example.manageUser;

import com.example.manageUser.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManageUserApplication implements CommandLineRunner {

	@Autowired
	private TimeService timeService;


	public static void main(String[] args) {
		SpringApplication.run(ManageUserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(timeService.getCurrentTime().getDateTime());
	}
}

package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@SpringBootTest
class Chapter03ApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("test입니다.");
	}
	
}

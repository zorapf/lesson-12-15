package com.goit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaDevApplication {

	public static void main(String[] args) {
			SpringApplication.run(JavaDevApplication.class, args);

		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("passwordEncoder.encode(\"user_password\") = " + passwordEncoder.encode("user_password"));
		System.out.println("passwordEncoder.encode(\"admin_password\") = " + passwordEncoder.encode("admin_password"));
		*/
	}

}

/*('user@mail.com', 'user_password', 'user'),
           ('admin@mail.com', 'admin_password', 'admin, user');*/

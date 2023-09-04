ALTER TABLE "user" ADD COLUMN password VARCHAR(200);
ALTER TABLE "user" ADD COLUMN authority VARCHAR(100);

INSERT INTO "user" (email, password, authority)
VALUES ('user@mail.com', '{noop}user_password', 'user'), /*No Encoding*/
           ('admin@mail.com', '{bcrypt}$2a$10$HqS/1mo/7mDcp/UA6x5G8.co2S.oNmlo/4kmnMJncppB/lfcokwae', 'admin, user');
/*Encoding - password = admin_password*/
/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("passwordEncoder.encode(\"user_password\") = " + passwordEncoder.encode("user_password"));
		System.out.println("passwordEncoder.encode(\"admin_password\") = " + passwordEncoder.encode("admin_password"));
		*/
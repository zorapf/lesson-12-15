/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.user;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-12
 */
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final SessionFactory sessionFactory;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean exists(String email) {
        if(email==null) return false;
        return userRepository.existsById(email); //var 00
        //User user = entityManager.find(User.class, email); //var 01
        /*Session session = sessionFactory.openSession();
        User user = session.find(User.class, email);
        session.close();//спустились на дно
        return user != null;*/
        /*Integer userCount = jdbcTemplate.queryForObject("SELECT count(*) FROM \"user\" WHERE email = :email",
                                                                Map.of("email", email),
                                                                Integer.class);
        return userCount == 1;*/
    }

    public void deleteById(String email) {
        userRepository.deleteById(email);
    }

    @Transactional
    public void deleteByIds(List<String> emails) {
        userRepository.deleteAllByEmails(emails);
        /*jdbcTemplate.update("DELETE FROM \"user\" WHERE email IN (:emails)",
                Map.of("emails", emails));*/
    }

    public List<User> search(String query) {
        //1 - return userRepository.search("%" +  query + "%");
        //2 - return userRepository.searchByNativeSql("%" +  query + "%");
        return userRepository.findAllById(
                userRepository.searchEmails("%" +  query + "%"));
    }

    public int countPeopleOlderThen(int age) {
        LocalDate maxBirthday = LocalDate.now().minusYears(age);
        return userRepository.countPeopleOlderThen(maxBirthday);
    }

    public UserInfo getUserInfo(String email) {
        String sql = "SELECT u.email as email, full_name, birthday, gender, address FROM \"user\" u" +
                     "LEFT JOIN user_address ua ON u.email = ua.email" +
                     "WHERE u.email = :email";
        List<UserInfoItem> items = jdbcTemplate.query(sql,
                Map.of("email", email),
                new UserInfoItemMapper());
        UserInfoItem firstItem = items.get(0);
        int age = (int) ChronoUnit.YEARS.between(firstItem.getBirthday(), LocalDate.now());
        UserDTO userDTO = UserDTO.builder()
                .email(firstItem.getEmail())
                .fullName(firstItem.getFullName())
                .birthday(firstItem.getBirthday())
                .gender(firstItem.getGender())
                .age(age)
                .build();
        List<String> addresses = items.stream()
                .map(UserInfoItem::getAddress)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        UserInfo result = new UserInfo();
        result.setUser(userDTO);
        result.setAddresses(addresses);

        return result;

    }

    public UserInfo getUserInfo2(String email) {
        User user = userRepository.findById(email).get();

        String sql = "SELECT address FROM user_address " +
                "WHERE email = :email";
        List<String> addresses = jdbcTemplate.queryForList(sql,
                Map.of("email", email),
                String.class);

        UserInfo result = new UserInfo();
        result.setUser(UserDTO.fromUser(user));
        result.setAddresses(addresses);
        return result;
    }

    private static class UserInfoItemMapper implements RowMapper<UserInfoItem> {

        @Override
        public UserInfoItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return UserInfoItem.builder()
                    .email(rs.getString("email"))
                    .fullName(rs.getString("full_name"))
                    .birthday(LocalDate.parse(rs.getString("birthday")))
                    .gender(Gender.valueOf(rs.getString("gender")))
                    .address(rs.getString("address"))
                    .build();
        }
    }

    @Builder
    @Data
    private static class UserInfoItem {
        private String email;
        private String fullName;
        private LocalDate birthday;
        private Gender gender;
        private String address;
    }

    public List<User> getUserBetween(LocalDate start, LocalDate end) {
        /*Specification<User> betweenSpec = (root, cq, cb) -> cb.and(
                cb.greaterThanOrEqualTo(root.get("birthday"), start),
                cb.lessThanOrEqualTo(root.get("birthday"), end)
        );
        return userRepository.findAll(betweenSpec);*/
        return userRepository.findAll((root, cq, cb) -> cb.between(root.get("birthday"), start, end));
    }
}

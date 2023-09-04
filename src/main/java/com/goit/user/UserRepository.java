/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-12
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query("from User u where lower(u.email) like lower(:query)  or lower(u.fullName)  like lower(:query)")
    List<User> search(@Param("query") String query);

    @Query(nativeQuery = true, value =
            "SELECT email, full_name, birthday, gender FROM \"user\" " +
            "WHERE lower(email) like lower(:query)  or lower(full_name)  like lower(:query)")
    List<User> searchByNativeSql(@Param("query") String query);

    @Query(nativeQuery = true, value =
            "SELECT email FROM \"user\" " +
            "WHERE lower(email) like lower(:query)  or lower(full_name)  like lower(:query)")
    List<String> searchEmails(@Param("query") String query);

    @Query(nativeQuery = true,
            value = "SELECT email FROM \"user\" WHERE birthday < :maxBirthday")
    int countPeopleOlderThen(@Param("maxBirthday") LocalDate maxBirthday);

    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE FROM \"user\" WHERE email in (:emails)")
    void deleteAllByEmails(@Param("emails") List<String> emails);
}

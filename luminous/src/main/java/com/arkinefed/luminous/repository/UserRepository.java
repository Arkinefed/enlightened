package com.arkinefed.luminous.repository;

import com.arkinefed.luminous.data.account.UserInformationAdmin;
import com.arkinefed.luminous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select new com.arkinefed.luminous.data.account.UserInformationAdmin(u.id, u.username, u.password, u.role, u.firstName, u.lastName, u.registerDate) from User u")
    List<UserInformationAdmin> getAllUsers();
}

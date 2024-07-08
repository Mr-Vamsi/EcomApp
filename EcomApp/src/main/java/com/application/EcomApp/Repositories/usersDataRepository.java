package com.application.EcomApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.application.EcomApp.Entities.usersData;

import jakarta.transaction.Transactional;

@Repository
public interface usersDataRepository extends JpaRepository<usersData ,Long> {
    public usersData findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update usersData u set u.username = :name, u.usermobile = :phone, u.userpassword = :password where u.email = :email")
    int updateUser(String name, String phone, String password, String email);
}

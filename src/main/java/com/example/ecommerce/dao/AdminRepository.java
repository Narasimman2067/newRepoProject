package com.example.ecommerce.dao;

import com.example.ecommerce.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query(value = "select * from Admin  where admin_email=:adminEmail and admin_password=:adminPassword", nativeQuery = true)
    List<Admin> getAdminByAdminEmailAndAdminPassword(@Param("adminEmail") String adminEmail , @Param("adminPassword") String adminPassword);
}

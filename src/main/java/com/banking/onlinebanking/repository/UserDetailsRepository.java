package com.banking.onlinebanking.repository;

import com.banking.onlinebanking.repository.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}

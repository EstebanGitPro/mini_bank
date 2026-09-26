package com.the_boys.mini_banck.repository;

import com.the_boys.mini_banck.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}

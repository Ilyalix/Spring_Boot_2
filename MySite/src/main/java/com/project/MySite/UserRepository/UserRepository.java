package com.project.MySite.UserRepository;

import com.project.MySite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
      User findByUsername(String username); // метод, который возращает пользователя (получение пользователя по имени)
}

package com.project.sweater.UserRepository;

import com.project.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// метод который возвращает пользователя
public interface UserRepository extends JpaRepository<User, Long> {
      User findByUsername(String username); // метод, который возращает пользователя (получение пользователя по имени)
}

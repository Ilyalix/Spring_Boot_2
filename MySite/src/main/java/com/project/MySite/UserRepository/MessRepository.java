package com.project.MySite.UserRepository;

import com.project.MySite.domain.Message;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Расширяя CrudRepository, MessRepository наследует несколько методов для работы с Messages,
// включая методы сохранения, удаления и поиска и т.д.
// Spring Data JPA -> не нужно писать реализацию интерфейса репозитория!
// Spring Data JPA создает реализацию, когда мы запускаем приложение

public interface MessRepository extends CrudRepository<Message, Long> {
            List<Message> findByTag(String tag);
            List<Message> deleteByText(String text);
}

package com.sunmeat.contactlist.repository;

import com.sunmeat.contactlist.model.Contact; // импорт класса Contact, который представляет сущность JPA, с которой будет работать репозиторий
import org.springframework.data.jpa.repository.JpaRepository; // импорт интерфейса JpaRepository из Spring Data JPA. этот интерфейс предоставляет стандартные методы для выполнения операций CRUD (создание, чтение, обновление, удаление) и другие полезные функции для работы с сущностями

// long: nип данных для первичного ключа сущности Contact
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

/*
 JpaRepository предоставляет множество методов для работы с данными в БД без необходимости их явного определения.
 некоторые из этих методов включают:
save(S entity): сохраняет переданную сущность. если сущность с таким идентификатором уже существует, она будет обновлена; иначе — будет создана новая
findById(ID id): находит сущность по её идентификатору. возвращает Optional с найденной сущностью или пустое значение, если сущность не найдена
findAll(): возвращает список всех сущностей
deleteById(ID id): удаляет сущность по её идентификатору
 */
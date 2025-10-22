package com.sunmeat.contactlist.repository;

import com.sunmeat.contactlist.model.Contact; // імпорт класу contact, що представляє сутність JPA, з якою працюватиме репозиторій
import org.springframework.data.jpa.repository.JpaRepository; // імпорт інтерфейсу JpaRepository зі spring data jpa. цей інтерфейс
// надає стандартні методи для виконання CRUD-операцій (створення, читання, оновлення, видалення) та інші корисні функції для роботи з сутностями

// long: тип даних для первинного ключа сутності Contact
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

/*
 * JpaRepository надає безліч методів для роботи з даними в БД без потреби їх
 * явного визначення. деякі з цих методів включають:
 * save(entity): зберігає передану сутність. якщо сутність з таким ідентифікатором уже існує, вона буде оновлена; інакше — буде створена нова
 * findbyid(id): знаходить сутність за її ідентифікатором. повертає optional з знайденою сутністю або порожнє значення, якщо сутність не знайдена
 * findall(): повертає список усіх сутностей
 * deletebyid(id): видаляє сутність за її ідентифікатором
 */

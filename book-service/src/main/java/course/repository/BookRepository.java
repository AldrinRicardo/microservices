package course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import course.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}

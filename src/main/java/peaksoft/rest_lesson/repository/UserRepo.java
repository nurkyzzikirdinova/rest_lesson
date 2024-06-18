package peaksoft.rest_lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.rest_lesson.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
Optional<User> getUserByEmail(String email);
}

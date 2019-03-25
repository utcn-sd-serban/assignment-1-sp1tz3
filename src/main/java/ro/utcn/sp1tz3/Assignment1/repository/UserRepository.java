package ro.utcn.sp1tz3.Assignment1.repository;

import ro.utcn.sp1tz3.Assignment1.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    User save(User user);
    void remove(User user);
    Optional<User> findById(int id);
}

package ro.utcn.sp1tz3.Assignment1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sp1tz3.Assignment1.entity.User;
import ro.utcn.sp1tz3.Assignment1.repository.UserRepository;

public interface DataUserRepository extends Repository<User, Integer>, UserRepository {

    void delete(User user);

    @Override
    default void remove(User user){ delete(user);}
}

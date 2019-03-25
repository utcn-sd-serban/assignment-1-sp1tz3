package ro.utcn.sp1tz3.Assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sp1tz3.Assignment1.entity.User;
import ro.utcn.sp1tz3.Assignment1.repository.RepositoryFactory;
import ro.utcn.sp1tz3.Assignment1.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {
    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception{
        UserRepository repository = factory.createUserRepository();
        if(repository.findAll().isEmpty()){
            repository.save(new User("a","a","regular",0));
            repository.save(new User("Sp1Tz3","haha","moderator",0));
            repository.save(new User("Allah","ackbar","regular",0));
            repository.save(new User("Hatz","johnule","regular",0));
        }
    }
}

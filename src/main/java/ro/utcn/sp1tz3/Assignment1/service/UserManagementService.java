package ro.utcn.sp1tz3.Assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sp1tz3.Assignment1.entity.User;
import ro.utcn.sp1tz3.Assignment1.exception.UserNotFoundException;
import ro.utcn.sp1tz3.Assignment1.repository.RepositoryFactory;
import ro.utcn.sp1tz3.Assignment1.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<User> listUsers(){ return repositoryFactory.createUserRepository().findAll();}

    @Transactional
    public User addUser(String username, String password, String usertype){
        return repositoryFactory.createUserRepository().save(new User(username, password, usertype, 0));
    }

    @Transactional
    public void removeUser(int id){
        UserRepository repo = repositoryFactory.createUserRepository();
        User user = repo.findById(id).orElseThrow(UserNotFoundException::new);
        repo.remove(user);
    }
}

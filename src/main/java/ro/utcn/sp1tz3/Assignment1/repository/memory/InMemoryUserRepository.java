package ro.utcn.sp1tz3.Assignment1.repository.memory;


import ro.utcn.sp1tz3.Assignment1.entity.User;
import ro.utcn.sp1tz3.Assignment1.repository.UserRepository;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Integer, User> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public List<User> findAll(){ return new ArrayList<>(data.values());}

    @Override
    public User save(User user){
        if(user.getUserid()==null){
            user.setUserid(currentId.incrementAndGet());
        }
        data.put(user.getUserid(), user);
        return user;
    }

    @Override
    public void remove(User user){
        data.remove(user.getUserid());
    }

    @Override
    public Optional<User> findById(int id){
        return Optional.ofNullable(data.get(id));
    }
}

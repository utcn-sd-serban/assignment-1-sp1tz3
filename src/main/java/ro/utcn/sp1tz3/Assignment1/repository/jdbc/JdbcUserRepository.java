package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.utcn.sp1tz3.Assignment1.entity.User;
import ro.utcn.sp1tz3.Assignment1.repository.UserRepository;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate template;

    @Override
    public List<User> findAll(){ return template.query("SELECT * FROM user", new UserMapper());}

    @Override
    public User save(User user){
        if(user.getUserid() == null){
            user.setUserid(insert(user));
        } else {
            update(user);
        }
        return user;
    }

    @Override
    public void remove(User user){ template.update("DELETE FROM user WHERE userid = ?", user.getUserid());}

    @Override
    public Optional<User> findById(int id){
        List<User> users = template.query("SELECT * FROM user WHERE userid = ?", new UserMapper(), id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    private int insert(User user){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("user");
        insert.usingGeneratedKeyColumns("userid");
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("usertype", user.getUsertype());
        map.put("score", user.getScore());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(User user){
        template.update("UPDATE user SET username = ?, password = ?, usertype = ?, score = ? WHERE userid = ?",
                user.getUsername(), user.getPassword(), user.getUsertype(), user.getScore(), user.getUserid());
    }


}

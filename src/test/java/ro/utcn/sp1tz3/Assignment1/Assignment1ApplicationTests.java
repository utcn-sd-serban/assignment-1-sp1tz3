package ro.utcn.sp1tz3.Assignment1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.entity.User;
import ro.utcn.sp1tz3.Assignment1.repository.UserRepository;
import ro.utcn.sp1tz3.Assignment1.repository.memory.InMemoryRepositoryFactory;
import ro.utcn.sp1tz3.Assignment1.repository.memory.InMemoryUserRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment1ApplicationTests {

	private InMemoryRepositoryFactory repoFactory = new InMemoryRepositoryFactory();
	private UserRepository userRepo = repoFactory.createUserRepository();

	private final Map<Integer, User> userData = new ConcurrentHashMap<>();
	private final Map<Integer, Question> questionData = new ConcurrentHashMap<>();

	@Test
	public void test1() {
		User newUser = new User(1,"name", "pass", "regular", 0);
		User newUser2 = new User(2,"name2", "pass2", "regular", 0);
		userData.put(newUser.getUserid(), newUser);
		userData.put(newUser2.getUserid(), newUser2);
		assert userData.get(1).equals(newUser);
		assert userData.get(2).equals(newUser2);
	}

	@Test
	public void test2(){
		User newUser = new User(1,"name", "pass", "regular", 0);
		userData.put(1,newUser);
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		Question newQuestion = new Question(1,1,"title", "text", ts, null);
		questionData.put(newQuestion.getQuestionid(), newQuestion);
		assert questionData.get(1).equals(newQuestion);
	}

}

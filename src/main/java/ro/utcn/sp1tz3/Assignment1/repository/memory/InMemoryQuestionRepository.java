package ro.utcn.sp1tz3.Assignment1.repository.memory;

import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryQuestionRepository implements QuestionRepository {

    private Map<Integer, Question> data = new ConcurrentHashMap<>();
    private AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Question save(Question question) {
        if(question.getQuestionid() == null){
            question.setQuestionid(currentId.incrementAndGet());
        }
        data.put(question.getQuestionid(), question);
        return question;
    }

    @Override
    public void remove(Question question) {
        data.remove(question.getQuestionid());
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }
}

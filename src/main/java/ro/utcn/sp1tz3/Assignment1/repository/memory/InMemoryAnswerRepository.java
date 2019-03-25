package ro.utcn.sp1tz3.Assignment1.repository.memory;

import ro.utcn.sp1tz3.Assignment1.entity.Answer;
import ro.utcn.sp1tz3.Assignment1.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryAnswerRepository implements AnswerRepository {

    private Map<Integer, Answer> data = new ConcurrentHashMap<>();
    private AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public List<Answer> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Answer save(Answer answer) {
        if(answer.getAnswerid() == null){
            answer.setAnswerid(currentId.incrementAndGet());
        }
        data.put(answer.getAnswerid(), answer);
        return answer;
    }

    @Override
    public void remove(Answer answer) {
        data.remove(answer.getAnswerid());
    }

    @Override
    public Optional<Answer> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }
}

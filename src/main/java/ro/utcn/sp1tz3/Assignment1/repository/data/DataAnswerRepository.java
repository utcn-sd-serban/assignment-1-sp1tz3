package ro.utcn.sp1tz3.Assignment1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sp1tz3.Assignment1.entity.Answer;
import ro.utcn.sp1tz3.Assignment1.repository.AnswerRepository;

public interface DataAnswerRepository extends Repository<Answer, Integer>, AnswerRepository {
    void delete(Answer answer);

    @Override
    default void remove(Answer answer){delete(answer);}
}

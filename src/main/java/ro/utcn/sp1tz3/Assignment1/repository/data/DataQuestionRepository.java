package ro.utcn.sp1tz3.Assignment1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.repository.QuestionRepository;
import ro.utcn.sp1tz3.Assignment1.repository.RepositoryFactory;

public interface DataQuestionRepository extends Repository<Question, Integer>, QuestionRepository {

    void delete(Question question);

    @Override
    default void remove(Question question){delete(question);}
}

package ro.utcn.sp1tz3.Assignment1.repository;

import ro.utcn.sp1tz3.Assignment1.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    List<Question> findAll();
    Question save(Question question);
    void remove(Question question);
    Optional<Question> findById(int id);
}

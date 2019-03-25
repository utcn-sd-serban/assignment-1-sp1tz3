package ro.utcn.sp1tz3.Assignment1.repository;

import ro.utcn.sp1tz3.Assignment1.entity.Answer;
import ro.utcn.sp1tz3.Assignment1.entity.Question;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    List<Answer> findAll();
    Answer save(Answer answer);
    void remove(Answer answer);
    Optional<Answer> findById(int id);
}

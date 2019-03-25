package ro.utcn.sp1tz3.Assignment1.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.entity.User;
import ro.utcn.sp1tz3.Assignment1.repository.QuestionRepository;
import ro.utcn.sp1tz3.Assignment1.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateQuestionRepository implements QuestionRepository {
    private final EntityManager entityManager;

    @Override
    public List<Question> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        query.select(query.from(Question.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Question save(Question question) {
        return entityManager.merge(question);
    }

    @Override
    public void remove(Question question) {
        entityManager.remove(question);
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(entityManager.find(Question.class, id));
    }
}

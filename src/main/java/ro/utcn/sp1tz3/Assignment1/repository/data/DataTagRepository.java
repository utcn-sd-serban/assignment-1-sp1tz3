package ro.utcn.sp1tz3.Assignment1.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sp1tz3.Assignment1.entity.Tag;
import ro.utcn.sp1tz3.Assignment1.repository.TagRepository;

public interface DataTagRepository extends Repository<Tag, Integer>, TagRepository {

    void delete(Tag tag);

    @Override
    default void remove(Tag tag){delete(tag);}
}

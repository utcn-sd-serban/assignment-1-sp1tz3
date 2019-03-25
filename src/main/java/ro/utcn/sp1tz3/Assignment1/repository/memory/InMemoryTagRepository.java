package ro.utcn.sp1tz3.Assignment1.repository.memory;

import ro.utcn.sp1tz3.Assignment1.entity.Tag;
import ro.utcn.sp1tz3.Assignment1.repository.TagRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTagRepository implements TagRepository {
    private final Map<Integer, Tag> data = new HashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Tag save(Tag tag) {
        if(tag.getTagid() == null){
            tag.setTagid(currentId.incrementAndGet());
        }
        data.put(tag.getTagid(), tag);
        return tag;
    }

    @Override
    public void remove(Tag tag) {
        data.remove(tag.getTagid());
    }

    @Override
    public Optional<Tag> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }
}

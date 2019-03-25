package ro.utcn.sp1tz3.Assignment1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sp1tz3.Assignment1.entity.Tag;
import ro.utcn.sp1tz3.Assignment1.repository.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {
    private final JdbcTemplate template;

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM tag", new TagMapper());
    }

    @Override
    public Tag save(Tag tag) {
        if(tag.getTagid() == null){
            tag.setTagid(insert(tag));
        } else {
            update(tag);
        }
        return tag;
    }

    @Override
    public void remove(Tag tag) {
        template.update("DELETE FROM tag WHERE tagid = ?", tag.getTagid());
    }

    @Override
    public Optional<Tag> findById(int id) {
        List<Tag> tags = template.query("SELECT * FROM tag WHERE tagid = ?", new TagMapper(), id);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    private int insert(Tag tag){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tag");
        insert.usingGeneratedKeyColumns("tagid");
        Map<String, Object> map = new HashMap<>();
        map.put("title", tag.getTitle());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(Tag tag){
        template.update("UPDATE tag SET title = ? WHERE tagid = ?",
                tag.getTitle(), tag.getTagid());
    }
}

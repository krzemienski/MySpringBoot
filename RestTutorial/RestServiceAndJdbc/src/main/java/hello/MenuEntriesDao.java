package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("myMenuEntriesDao")
@Lazy
public class MenuEntriesDao {

    private List<String> menuEntries;

    @Autowired
    public MenuEntriesDao(JdbcTemplate jdbcTemplate) {
        menuEntries = jdbcTemplate.query(
                "SELECT entry FROM menu_entries",
                (rs, rowNum) ->rs.getString("entry"));
    }

    public List<String> getMenuEntries() {
        return menuEntries;
    }
}
package k5s.hotmovie.repository;

import k5s.hotmovie.domain.HotMovie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplateRepository implements MovieRepository{

    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplateRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<HotMovie> findByCode(Long code) {
        return jdbcTemplate.query("select * from mv_table where code = ?", memberRowMapper(), code);
    }
    @Override
    public List<HotMovie> findAll() {
        return jdbcTemplate.query("select * from mv_table", memberRowMapper());
    }

    private RowMapper<HotMovie> memberRowMapper() {
        return (rs, rowNum) -> {
            HotMovie movie = new HotMovie();
            movie.setCode(rs.getLong("code"));
            movie.setTitle(rs.getString("title"));
            movie.setNation(rs.getString("nation"));
            movie.setRating(rs.getString("rating"));
            movie.setScore(rs.getDouble("score"));
            movie.setGenre(rs.getString("genre"));
            movie.setDirector(rs.getString("director"));
            movie.setActor(rs.getString("actor"));
            movie.setStory(rs.getString("story"));
            movie.setOpening_date(rs.getString("opening_date"));
            movie.setRunning_time(rs.getString("running_time"));
            movie.setImg_url(rs.getString("img"));
            return movie;
        };
    }
}
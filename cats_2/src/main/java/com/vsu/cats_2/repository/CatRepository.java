package com.vsu.cats_2.repository;

import com.vsu.cats_2.Entity.Cat;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CatRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CatRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long create(@Valid Cat aCat) {
        return namedParameterJdbcTemplate.queryForObject(
                "INSERT INTO cats(name_cat, age_cat, id_profile) Values(:name_cat,:age_cat,:id_profile) RETURNING id_cat ",
                Map.of(
                        "cat_name", aCat.getCatName(),
                        "age_cat", aCat.getAge(),
                        "id_profile", aCat.getCatPersonId()
                ),
                Long.class
        );
    }

    public int update(@Valid Cat aCat) {
        return namedParameterJdbcTemplate.update(
                "UPDATE cats SET name_cat = :name_cat, age_cat = :age_cat WHERE id_cat= :id_cat",
                Map.of(
                        "name_cat", aCat.getCatName(),
                        "age_cat", aCat.getAge(),
                        "id_cat", aCat.getId()

                )
        );
    }

    public Optional<Cat> findById(Long id_cat) {
        return Optional.ofNullable(namedParameterJdbcTemplate.query(
                "SELECT id_cat, name_cat, age_cat, id_profile FROM cats WHERE id_cat = :id_cat",
                Map.of("id_cat",id_cat),
                rs -> {
                    if (rs.next()) {
                        return new Cat(
                                rs.getLong("id_cat"),
                                rs.getString("name_cat"),
                                rs.getFloat("age_cat"),
                                rs.getLong("id_profile")
                        );
                    }
                    return null;
                }
        ));
    }

    public Optional<List<Cat>> findByCatName(Long profileId, String name_cat) {

        List<Cat> cats = namedParameterJdbcTemplate.query(
                "SELECT id_cat, name_cat, age_cat, id_profile FROM cat " +
                        "WHERE profile_id = :profileId AND site_address LIKE :name_cat",
                Map.of(
                        "profileId", profileId,
                        "name_cat", "%" + name_cat + "%"
                ),
                (rs, rowNum) -> new Cat(
                        rs.getLong("id_cat"),
                        rs.getString("name_cat"),
                        rs.getFloat("age_cat"),
                        rs.getLong("id_profile")
                )
        );

        return cats.isEmpty() ? Optional.empty() : Optional.of(cats);
    }

    public int deleteById(Long id_cat) {
        return namedParameterJdbcTemplate.update(
                "DELETE FROM cat WHERE id_cat = :id_cat",
                Map.of("id_cat", id_cat)
        );
    }
}

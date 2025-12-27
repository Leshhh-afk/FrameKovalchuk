package com.vsu.cats_2.repository;

import com.vsu.cats_2.Entity.CatPerson;
import com.vsu.cats_2.request.CreateCatPersonRequest;
import com.vsu.cats_2.utils.PasswordHasher;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class CatPersonRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CatPersonRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<CatPerson> findByLogin(String login) {
        return namedParameterJdbcTemplate.query(
                "SELECT id, login, password_hash, salt FROM profile WHERE login = :login",
                Map.of("login", login),
                rs -> {
                    if (rs.next()) {
                        return Optional.of(
                                new CatPerson(rs.getLong("id"),
                                        rs.getString("login"),
                                        rs.getString("password_hash"),
                                        rs.getString("salt")));
                    }
                    return Optional.empty();
                });
    }

    public int create(@Valid CreateCatPersonRequest createCatPersonRequest) {
        String salt = PasswordHasher.generateSalt();
        return namedParameterJdbcTemplate.update("INSERT INTO profile(login,password_hash,salt) Values(:login,:password_hash,:salt)",
        Map.of(
                "login",createCatPersonRequest.getCatPersonName(),
                "password_hash",PasswordHasher.hashPassword( createCatPersonRequest.getPassword(),salt),
                "salt", salt
        ));
    }

    public Optional<CatPerson> findByID(long catPersonId) {
        return namedParameterJdbcTemplate.query(
                "select id, login, password_hash, salt from profile where id = :id",
                Map.of("id", catPersonId),
                rs -> {
                    if (rs.next()) {
                        return Optional.of( new CatPerson(
                                rs.getLong("id"),
                                rs.getString("login"),
                                rs.getString("password_hash"),
                                rs.getString("salt")));
                    }
                    return Optional.empty();
                });
    }
}

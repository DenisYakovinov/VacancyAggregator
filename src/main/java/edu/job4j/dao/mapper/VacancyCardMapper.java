package edu.job4j.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

import edu.job4j.service.entity.VacancyCard;

public class VacancyCardMapper implements RowMapper<VacancyCard> {

    @Override
    public VacancyCard mapRow(ResultSet resultSet) throws SQLException {
        String[] meta = (String[]) resultSet.getArray("meta_tags").getArray();
        String[] skills = (String[]) resultSet.getArray("skills").getArray();
        return VacancyCard.builder().vacancyCardId(resultSet.getLong("vacancy_id"))
                                    .date(resultSet.getDate("vacancy_date").toLocalDate())
                                    .company(resultSet.getString("company"))
                                    .position(resultSet.getString("position"))
                                    .salary(resultSet.getString("salary"))
                                    .link(resultSet.getString("link"))
                                    .metaTags(new HashSet<>(Arrays.asList(meta)))
                                    .skills(new HashSet<>(Arrays.asList(skills)))
                                    .build();
    }
}

package edu.job4j.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import edu.job4j.dao.mapper.VacancyCardMapper;
import edu.job4j.service.entity.VacancyCard;

public class VacancyCardDaoImpl extends GenericDao<VacancyCard> implements VacancyCardDao {

    private static final String ADD_VACANCY_QUERY = "INSERT INTO vacancy_cards (vacancy_date, company, position,"
            + " salary, link, meta_tags, skills) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public VacancyCardDaoImpl(ConnectionPool connectionPool) {
        super(connectionPool);

    }

    @Override
    public void addNew(VacancyCard vacancyCard) {
        int id = addNew(vacancyCard, ADD_VACANCY_QUERY);
        vacancyCard.setVacancyId(id);
    }

    @Override
    public void addAll(List<VacancyCard> vacancyCards) {
        addAllEntities(vacancyCards, ADD_VACANCY_QUERY);
    }

    @Override
    public List<VacancyCard> findAll() {
        return findAllEntities("SELECT * FROM vacancy_cards", new VacancyCardMapper());
    }

    @Override
    protected void mapFromEntity(PreparedStatement statement, VacancyCard card, Connection connection)
            throws SQLException {
        statement.setDate(1, Date.valueOf(card.getDate()));
        statement.setString(2, card.getCompany());
        statement.setString(3, card.getPosition());
        statement.setString(4, card.getSalary());
        statement.setString(5, card.getLink());
        Object[] tags = card.getMetaTags().toArray(new Object[0]);
        Object[] skills = card.getSkills().toArray(new Object[0]);
        statement.setArray(6, connection.createArrayOf("text", tags));
        statement.setArray(7, connection.createArrayOf("text", skills));
    }
}

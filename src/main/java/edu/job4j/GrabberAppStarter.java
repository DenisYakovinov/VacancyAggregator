package edu.job4j;

import java.io.IOException;
import java.util.List;

import edu.job4j.service.VacancyParserHabrImpl;
import edu.job4j.dao.ConnectionPool;
import edu.job4j.dao.VacancyCardDao;
import edu.job4j.dao.VacancyCardDaoImpl;
import edu.job4j.service.VacancyParser;
import edu.job4j.service.entity.VacancyCard;

public class GrabberAppStarter {

    public static void main(String[] args) throws IOException {
        VacancyParser parser = new VacancyParserHabrImpl();
        List<VacancyCard> vacanciesCards = parser.parse();
        System.out.println(vacanciesCards);
        ConnectionPool connectionPool = new ConnectionPool("application.properties");
        VacancyCardDao vacancyCardDao = new VacancyCardDaoImpl(connectionPool);
        vacancyCardDao.addAll(vacanciesCards);
        List<VacancyCard> cards = vacancyCardDao.findAll();
        System.out.println(cards);
    }
}

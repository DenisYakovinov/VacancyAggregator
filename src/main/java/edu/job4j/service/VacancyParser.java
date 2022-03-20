package edu.job4j.service;

import java.io.IOException;
import java.util.List;

import edu.job4j.service.entity.VacancyCard;

public interface VacancyParser  {
    public List<VacancyCard> parse() throws IOException;
}

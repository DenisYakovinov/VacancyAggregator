package edu.job4j.dao;

import java.util.List;

import edu.job4j.service.entity.VacancyCard;

public interface VacancyCardDao {
 
    void addNew(VacancyCard vacancyCard);
    
    void addAll(List<VacancyCard> vacancyCards);
        
    public List<VacancyCard> findAll();
}

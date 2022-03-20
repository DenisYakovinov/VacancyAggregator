package edu.job4j.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.job4j.service.entity.VacancyCard;

public class VacancyParserHabrImpl implements VacancyParser {

    private static final String HABR_VACANCY_URL = "https://career.habr.com/vacancies/java_developer";
    private static final String HABR_URL = "https://career.habr.com";
    private static final String CONVERT_CLASS_LINE = "preserve-line";
          
    @Override
    public List<VacancyCard> parse() throws IOException {
        List<VacancyCard> vacanciesCards = new ArrayList<>();  
        Document doc = Jsoup.connect(HABR_VACANCY_URL).get();
        vacanciesCards.addAll(parseOnePage(doc));
        String nextPageLink = retriveNextPageLink(doc);
        while (!nextPageLink.equals("")) {      
            doc = Jsoup.connect(HABR_URL + nextPageLink).get();
            vacanciesCards.addAll(parseOnePage(doc));
            nextPageLink = retriveNextPageLink(doc);
        }
        return vacanciesCards;
    }
    
    private List<VacancyCard> parseOnePage(Document doc) {
        Elements vacancies = doc.getElementsByClass("vacancy-card");
        List<VacancyCard> vacanciesCards = new ArrayList<>();           
        vacancies.forEach(e -> {
            Elements skillsElements =  e.getElementsByClass("vacancy-card__skills");
            Elements metaElements = e.getElementsByClass("vacancy-card__meta");
            Set<String> metaTags = convertToSet(metaElements);
            Set<String> skills = convertToSet(skillsElements);                         
            vacanciesCards.add(
            VacancyCard.builder().date(LocalDate.parse(e.getElementsByTag("time").attr("datetime").substring(0, 10)))
                                 .company(e.getElementsByClass("vacancy-card__company-title").text())
                                 .link(HABR_URL + e.getElementsByClass("vacancy-card__title-link").attr("href"))
                                 .position(e.getElementsByClass("vacancy-card__title-link").text())
                                 .salary(e.getElementsByClass("basic-salary").text())
                                 .skills(skills)
                                 .metaTags(metaTags)
                                 .build());
         
        });
        return vacanciesCards;
    }
    
    private Set<String> convertToSet(Elements elements) {  
        HashSet<String> result = new HashSet<>();
        elements.forEach(element -> element.getElementsByClass(CONVERT_CLASS_LINE)
                .forEach(nestedElement -> result.add(nestedElement.getElementsByClass(CONVERT_CLASS_LINE).text()))

        );
        return result;
    }
    
    private String retriveNextPageLink(Document doc) {
        return doc.getElementsByAttributeValue("class", "page  next").attr("href");
    }
}

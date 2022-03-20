package edu.job4j.service.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class VacancyCard {

    private long vacancyId;  
    private LocalDate date;
    private String company;
    private String position;
    private String link;
    private String salary;
    private Set<String> skills;
    private Set<String> metaTags;

    public long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public Set<String> getMetaTags() {
        return metaTags;
    }

    public void setMetaTags(Set<String> metaTags) {
        this.metaTags = metaTags;
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, date, link, metaTags, position, salary, skills, vacancyId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VacancyCard)) {
            return false;
        }
        VacancyCard other = (VacancyCard) obj;
        return Objects.equals(company, other.company) && Objects.equals(date, other.date)
                && Objects.equals(link, other.link) && Objects.equals(metaTags, other.metaTags)
                && Objects.equals(position, other.position) && Objects.equals(salary, other.salary)
                && Objects.equals(skills, other.skills) && vacancyId == other.vacancyId;
    }

    @Override
    public String toString() {
        return "VacancyCard [vacancyId=" + vacancyId + ", date=" + date + ", company=" + company + ", position="
                + position + ", link=" + link + ", salary=" + salary + ", skills=" + skills + ", metaTags=" + metaTags
                + "]";
    }

    public static VacancyCardBuilder builder() {
        return new VacancyCardBuilder();
    }

    public static class VacancyCardBuilder {

        private long vacancyCardId;
        private LocalDate date;
        private String company;
        private String position;
        private String link;
        private String salary;
        private Set<String> skills;
        private Set<String> metaTags;
        
        VacancyCardBuilder() {
        }
        
        public VacancyCardBuilder vacancyCardId(Long vacancyId) {
            this.vacancyCardId = vacancyId;
            return this;
        }
        
        public VacancyCardBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public VacancyCardBuilder company(String company) {
            this.company = company;
            return this;
        }

        public VacancyCardBuilder position(String position) {
            this.position = position;
            return this;
        }

        public VacancyCardBuilder link(String link) {
            this.link = link;
            return this;
        }

        public VacancyCardBuilder salary(String salary) {
            this.salary = salary;
            return this;
        }

        public VacancyCardBuilder skills(Set<String> skills) {
            if (this.skills == null) {
                this.skills = new HashSet<>();
            }
            this.skills.addAll(skills);
            return this;
        }

        public VacancyCardBuilder metaTags(Set<String> metaTags) {
            if (this.metaTags == null) {
                this.metaTags = new HashSet<>();
            }
            this.metaTags.addAll(metaTags);
            return this;
        }

        public VacancyCard build() {
            VacancyCard card = new VacancyCard();
            card.setVacancyId(vacancyCardId);
            card.setDate(date);
            card.setCompany(company);
            card.setPosition(position);
            card.setLink(link);
            card.setSalary(salary);
            card.setMetaTags(metaTags);
            card.setSkills(skills);
            return card;
        }
    }
}

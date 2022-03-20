CREATE TABLE vacancy_cards (
    vacancy_id SERIAL NOT NULL PRIMARY KEY,
    vacancy_date Date,
    company varchar(300),
    position varchar(300),
    salary varchar(150),
    link varchar(300),
    meta_tags TEXT[],
    skills TEXT[]
);
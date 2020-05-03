package com.example.demobootweb;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class PersonFormatter implements Formatter<Person> {

    @Override
    public Person parse(String s, Locale locale) throws ParseException {
        Person person = new Person();
        person.setName(s);
        return person;
    }

    @Override
    public String print(Person person, Locale locale) {
        return person.toString();
    }
}

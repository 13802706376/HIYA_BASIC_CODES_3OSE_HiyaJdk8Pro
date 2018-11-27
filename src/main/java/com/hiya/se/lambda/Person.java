package com.hiya.se.lambda;

import java.time.LocalDate;

public class Person
{
    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    String name;
    LocalDate birthday;

    public LocalDate getBirthday() {
        return birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }
    

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

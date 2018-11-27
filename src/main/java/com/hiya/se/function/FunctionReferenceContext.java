package com.hiya.se.function;

import com.hiya.se.lambda.Person;

public class FunctionReferenceContext
{
    
    public FunctionReferenceContext()
    {
    }
    
    public String stringOp(IStringFunc sf, String s)
    {
        return sf.func(s);
    }

    public static<T> int compareMC(MyClass a, MyClass b)
    {
        return a.getValue() - b.getValue();
    }

    public <T> int compareByName(Person a, Person b)
    {
        return a.getName().compareTo(b.getName());
    }

    public int compareByAge(Person a, Person b)
    {
        return a.getBirthday().compareTo(b.getBirthday());
    }

    
}

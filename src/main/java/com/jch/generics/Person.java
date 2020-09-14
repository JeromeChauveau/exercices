package com.jch.generics;

public class Person implements Comparable<Object> {

    private final String name;

    private final int age;

    public Person (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Person { name: %s, age: %s }", name, age);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Person) {
            return Integer.compare(getAge(), ((Person) o).getAge());
        } else {
            System.out.println("object type: " + o.getClass().getName());
            return 0;
        }
    }
}

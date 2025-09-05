package com.example;

import java.util.Objects;

/**
 * A class representing a Person.
 * It implements the Comparable interface to define a "natural order".
 * In this case, the natural order is by personId.
 */
public class Person implements Comparable<Person> {
    private final int personId;
    private final String name;
    private final int age;

    public Person(int personId, String name, int age) {
        this.personId = personId;
        this.name = name;
        this.age = age;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + personId +
               ", name='" + name + '\'' +
               ", age=" + age +
               '}';
    }

    /**
     * This is the implementation of the compareTo method from the Comparable interface.
     * It defines the natural ordering for Person objects.
     * @param other The other Person object to compare to.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Person other) {
        // We can simply use Integer.compare to safely compare the IDs.
        return Integer.compare(this.personId, other.personId);
    }

    // It's good practice to override equals() and hashCode() when dealing with collections.
    // We will base them on the unique personId.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}

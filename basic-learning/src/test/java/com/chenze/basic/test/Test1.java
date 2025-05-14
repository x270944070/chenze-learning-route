package com.chenze.basic.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Test1 {

    @Test
    public void test1() {
        Person p1 = new Person("Aa", "BB");
        Person p2 = new Person("BB", "Aa");

        System.out.println(p1.equals(p2));      // false
        System.out.println(p1.hashCode() == p2.hashCode());  // true

        Set<Person2> set = new HashSet<>();
        set.add(new Person2("Alice"));
        System.out.println(set.contains(new Person2("Alice"))); // false
    }

    @Data
    @AllArgsConstructor
    public class Person {
        private String name;
        private String email;

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Person)) return false;
            Person other = (Person) obj;
            return Objects.equals(name, other.name) &&
                    Objects.equals(email, other.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, email);
        }


    }


    @Data
    @AllArgsConstructor
    public class Person2 {
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person2 person2 = (Person2) o;
            return Objects.equals(name, person2.name);
        }

    }

}

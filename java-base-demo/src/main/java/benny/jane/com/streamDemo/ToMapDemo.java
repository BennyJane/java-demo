package benny.jane.com.streamDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class ToMapDemo {
    public static void main(String[] args) {
        listToMapTest();
        sameKeyTest();
        valueOfNullTest();
    }

    static void listToMapTest() {
        Map<String, String> map;
        List<Person> people = new ArrayList() {
            {
                add(new Person("benny1", "月球"));
                add(new Person("benny2", "地球"));
                add(new Person("benny3", "火星"));
            }
        };
        map = people.stream().collect(Collectors.toMap(Person::getName, Person::getSite));
        System.out.println(map);
    }

    static void sameKeyTest() {
        Map<String, String> map;
        List<Person> people = new ArrayList() {
            {
                add(new Person("benny1", "月球"));
                add(new Person("benny1", "地球"));
                add(new Person("benny3", "火星"));
            }
        };
        map = people.stream()
                .collect(Collectors.toMap(Person::getName, Person::getSite, (oldValue, newValue) -> oldValue));
        System.out.println(map);

        map = people.stream()
                .collect(Collectors.toMap(Person::getName, Person::getSite, (oldValue, newValue) -> oldValue + newValue));
        System.out.println(map);
    }

    static void valueOfNullTest() {
        Map<String, String> map;
        List<Person> people = new ArrayList() {
            {
                add(new Person("benny1", null));
                add(new Person(null, "地球"));
                add(new Person("benny3", "火星"));
            }
        };
        map = people.stream()
                .collect(Collectors.toMap(Person::getName, person -> Optional.ofNullable(person.getSite()).orElse("default")));
        System.out.println(map);
    }

    static class Person {
        public String name;
        public String site;

        public Person(String name, String site) {
            this.name = name;
            this.site = site;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }
    }
}

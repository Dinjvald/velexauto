package lv.velexauto.velex.domain;

/**
 * Created by Dinjvald on 20/10/15.
 */
public class Human {

    private String name;
    private String surname;
    private int age;
    private double height;

    private Human(HumanBuilder hb) {
        this.name = hb.nameBuilder;
        this.surname = hb.surnameBuilder;
        this.age = hb.ageBuilder;
        this.height = hb.heightBuilder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public static class HumanBuilder {
        private String nameBuilder;
        private String surnameBuilder;
        private int ageBuilder;
        private double heightBuilder;

        public HumanBuilder name(String newName) {
            this.nameBuilder = newName;
            return this;
        }

        public HumanBuilder surname(String newSurname) {
            this.surnameBuilder = newSurname;
            return this;
        }

        public HumanBuilder age(int newAge) {
            this.ageBuilder = newAge;
            return this;
        }

        public HumanBuilder height(double newHeight) {
            this.heightBuilder = newHeight;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }
}

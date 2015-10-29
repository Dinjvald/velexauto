package lv.velexauto.velex.domain;

/**
 * Created by Dinjvald on 20/10/15.
 */
public class testMain {

    public static void main(String[] args) {

        Human deniss = new Human.HumanBuilder()
                .name("Deniss")
                .surname("Besk")
                .age(27)
                .height(180.2)
                .build();
        System.out.println(deniss.getName() + "\n" + deniss.getSurname() + "\n" + deniss.getAge() + "\n"
                + deniss.getHeight());

        deniss.setAge(20);

        System.out.println(deniss.getName() + "\n" + deniss.getSurname() + "\n" + deniss.getAge() + "\n"
                + deniss.getHeight());

    }
}

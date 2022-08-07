package test;

public class Person{
    private String name;
    public Person() {
        System.out.println("Person无参构造方法");
    }

    public Person(String name) {
        this.name = name;
        System.out.println("调用有参构造" + name.toString());
    }
}

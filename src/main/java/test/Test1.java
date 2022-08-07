package test;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;

public class Test1 {
    public static void main(String[] args) throws Exception {
        //获得Person的有参构造函数
        Constructor<Person> personConst = Person.class.getDeclaredConstructor(String.class);
        //获得Person的Constructor对象
        Constructor constructor = ReflectionFactory.getReflectionFactory().newConstructorForSerialization(
                User.class,
                personConst
        );
        //第一个参数是需要创建对象的Class，比如User.class
		//第二个参数是对象的父类的构造方法，比如Person.class.getDeclaredConstructor()或者								//Person.class.getDeclaredConstructor(String.class)
        //constructor.setAccessible(true);
        //实例化对象，给person构造方法传入xxx，并转型成User
        User user = (User) constructor.newInstance("xxx");
        user.eat();
    }
}
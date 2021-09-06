import entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test03 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获取class对象
        Class c1 =Class.forName("com.yt.ReflectionMechanism.User");
        User user1 = (User) c1.newInstance();
        System.out.println(user1);
        //通过构造器创建对象
        Constructor constructor =c1.getDeclaredConstructor(int.class,String.class,int.class);
        User user2 = (User) constructor.newInstance(1,"A",1);
        System.out.println(user2);
        //通过反射调用普通方法
        User user3 = (User) c1.newInstance();
        Method setName = c1.getDeclaredMethod("SetName", String.class);
        setName.invoke(user3,"S");
        System.out.println(user3.getName());
        //通过反射操作属性
        User user4 = (User) c1.newInstance();
        Field name = c1.getDeclaredField("name");
        name.setAccessible(true);
        name.set(user4,"D");
        System.out.println(user4.getName());
    }
}

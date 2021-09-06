/***
 *
 * 函数式接口
 */
public class SingleAbstractMethodTest {
    public static void main(String[] args) {

    }

}
@FunctionalInterface
interface IStudent {
    void study(String technology);
    static  void play(){
        System.out.println("函数式静态方法");
    }
    //接口不能直接使用
    default void sleep(int time){
        System.out.println("接口等待"+time);
    }
}
@FunctionalInterface
interface ITeacher {
    void teach(String technology);
    static  void play(){
        System.out.println("函数式静态方法");
    }
    //接口不能直接使用
    default void sleep(int time){
        System.out.println("接口等待"+time);
    }
}

class Student implements IStudent{

    @Override
    public void study(String technology) {
        System.out.println("学习"+technology);
    }

    public static void main(String[] args) {
       IStudent student = new Student();
       student.study("a");
       IStudent.play();
    }
}
//实现的接口中有相同的方法必须重写相同方法
//每个父接口只能有一个抽象方法而且必须相同
class Person implements IStudent,ITeacher {
    @Override
    public void study(String technology) {

    }

    @Override
    public void sleep(int time) {
        IStudent.super.sleep(time);
    }

    @Override
    public void teach(String technology) {

    }
}


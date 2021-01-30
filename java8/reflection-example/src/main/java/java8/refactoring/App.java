package java8.refactoring;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        Class<?> bookClass = Class.forName("java8.refactoring.Book");
        // Deprecated
        // bookClass.newInstance();
        Constructor<?> constructor = bookClass.getConstructor(String.class);
        Book book = (Book) constructor.newInstance("myBook"); // 파라미터 없으면 에러!
        System.out.println(book);

        Field a = Book.class.getDeclaredField("A");
        System.out.println(a.get(null)); //static 변수이기 때문에 그냥 넘겨주면 된다.
        a.set(null, "AAAAAAAAA");
        System.out.println(a.get(null));

        Field b = Book.class.getDeclaredField("B");
//        System.out.println(b.get(null)); 그냥은 가져올 수 없다! 넘겨주어야 가져올 수 있음
        b.setAccessible(true); //private 기 때문에 Accessible을 true로!
        System.out.println(b.get(book)); //인자도 넘겨주어야 가져올 수 있다
        b.set(book, "BBBBBB");
        System.out.println(b.get(book));

        Method c = Book.class.getDeclaredMethod("c");
        c.setAccessible(true);
        c.invoke(book);//인스턴스와 파라미터를 넘김

//        Method d = Book.class.getDeclaredMethod("sum"); 에러, 파라미터 없다고 넘긴 것! 파라미터도 넘기자
        Method d = Book.class.getDeclaredMethod("sum", int.class, int.class); //primitive type도 구분한다!
        int invoke = (int) d.invoke(book, 1, 2);
        System.out.println(invoke);


    }

    /*
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;

//        Book book = new Book();
//        Class<? extends Book> aClass = book.getClass();
//
//        Class<?> aClass1 = Class.forName("java8.refactoring"); /

        // public만 출력된다ㅠㅠ
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        // 모두 가져옴!
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        // modifire도 접근해 구체적으로 확인 가능, 파라미터, 리턴타입 등 모두 가능
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });

        Book book = new Book();
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true); // 접근이 불가능한 경우 무시하고 모두 접근하도록 설정 가능
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        // 상위 클래스의 것까지 모두 접근
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

        // 생성자도 접근 가능
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);

        // 부모 클래스도 접근 가능
        System.out.println(MyBook.class.getSuperclass());

        // 인터페이스도 접근 가능
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);

        // 원래는 조회가 안된다.
        // Why? 근본적으로 주석과 유사. 클래스까지는 남지만 ByteCode를 로딩했을 때 빼고 읽는다
        // 때문에 @Retention(RetentionPolicy.RUNTIME)을 Annotation에 붙여야 조회가능
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);

        // 나에게 선언된 Annotation만!
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
    }
     */
}

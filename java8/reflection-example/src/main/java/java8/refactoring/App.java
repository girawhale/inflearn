package java8.refactoring;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
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
}

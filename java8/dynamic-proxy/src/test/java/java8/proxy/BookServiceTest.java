package java8.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookServiceTest {
//    컴파일
//    BookService bookService = new DefaultBookService();

//    런타임. But 제약사항 존재 인터페이스기반만 가능하고 클래스는 사용 불가...
    BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
            new InvocationHandler() {
                BookService bookService = new DefaultBookService(); // 리얼 서브젝트

                // 메소드마다 적용하지 않아도 되는 장점은 있지만 유연하지 못하다....
                // 그래서 스프링 AOP를 사용한다!
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("rent")) {
                        System.out.println("prev");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("post");
                        return invoke;
                    }

                    return method.invoke(bookService, args);
                }
            });


    @Test
    public void di() {
        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);

        bookService.returnBook(book);
    }
}

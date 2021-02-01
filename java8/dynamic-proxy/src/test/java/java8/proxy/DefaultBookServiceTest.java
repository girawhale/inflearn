package java8.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class DefaultBookServiceTest {

    @Test
    public void cglibClassProxy() {
        MethodInterceptor handler = new MethodInterceptor() {
            DefaultBookService bookService = new DefaultBookService();

            // o는 인터셉터를 나타냄
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("prev");
                    Object invoke = method.invoke(bookService, args);// 리얼 서브젝트, 인자값
                    System.out.println("post");
                    return invoke;
                }
                return method.invoke(bookService, args);
            }
        };
        DefaultBookService bookService = (DefaultBookService) Enhancer.create(DefaultBookService.class, handler);

        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);

        bookService.returnBook(book);
    }

    @Test
    public void byteBuddyClassProxy() throws Exception {
        // 인스턴스를 바로 만들어주지 않고 클래스를 만들어 줘야함
        Class<? extends DefaultBookService> proxyClass = new ByteBuddy().subclass(DefaultBookService.class)
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    DefaultBookService bookService = new DefaultBookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("prev");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("post");
                        return invoke;
                    }
                }))
                .make().load(DefaultBookService.class.getClassLoader()).getLoaded();
        DefaultBookService bookService = proxyClass.getConstructor(null).newInstance();

        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);

        bookService.returnBook(book);
    }
}

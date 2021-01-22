package java8.refactoring;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD}) // 붙일 수 있는 위치 제한
@Inherited // 상속이 되는 Annotation. Book을 상속받는 MyBook에도 붙게 됨
public @interface MyAnnotation {

    String value(); // 타입의 제한이 있다. List, Integer 등

    int number() default 100; // 값을 생성해두면 Annotation 을 붙일 때 값을 할당해야 함
}

package java8.annomagic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // Interface, Enum, Class 에 모두 붙일 수 있음
@Retention(RetentionPolicy.SOURCE)// 런타임과 바이트 모두 필요없다! 소스에서만 있으면 됨
public @interface Magic {
}

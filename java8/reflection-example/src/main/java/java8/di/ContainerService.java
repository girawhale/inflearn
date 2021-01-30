package java8.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            if (f.getAnnotation(Inject.class) != null) {
                Object fieldInstance = createInstance(f.getType());
                f.setAccessible(true);
                try {
                    f.set(instance, fieldInstance);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        return instance;
    }

    public static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();// 소스코드에서는 테스트 코드를 참조하지 못함 ㅠㅠ
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            throw new RuntimeException();
        }
    }
}

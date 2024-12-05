package main.java.somePackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InjectorTest {

    private Injector injector;

    @BeforeEach
    public void setUp() {
        injector = new Injector();
    }

    @Test
    public void testInject_ShouldInjectDependencies() throws Exception {
        SomeBean someBean = new SomeBean();
        someBean = injector.inject(someBean);

        // Проверяем, что зависимости были инъектированы
        Field field1 = SomeBean.class.getDeclaredField("field1");
        field1.setAccessible(true);
        Object value1 = field1.get(someBean);

        Field field2 = SomeBean.class.getDeclaredField("field2");
        field2.setAccessible(true);Object value2 = field2.get(someBean);

        assertNotNull(value1);
        assertNotNull(value2);

        // Вызываем метод foo() для проверки работы инъектированных объектов
        someBean.foo(); // Ожидаем печать 'AC'
    }
}
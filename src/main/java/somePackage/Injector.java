package main.java.somePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс Injector отвечает за инъекцию зависимостей в объекты.
 */
public class Injector {
    private static Properties properties;

    /**
     * Загружает свойства из файла конфигурации.
     *
     * @throws IOException если возникла ошибка при чтении файла
     */
    private void downloadProperties() throws IOException{
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
        properties = new Properties();
        properties.load(fileInputStream);
}

    /**
     * Инъектирует зависимости в указанный объект.
     *
     * @param object объект, в который будут инъектироваться зависимости
     * @param <T> тип объекта
     * @return объект с инъектированными зависимостями
     * @throws ClassNotFoundException если класс не найден
     * @throws IllegalAccessException если доступ к классу запрещен
     * @throws InstantiationException если не удается создать экземпляр класса
     * @throws IOException если возникла ошибка при чтении файла конфигурациии
     */
public <T> T inject(T object) throws ClassNotFoundException, IllegalAccessException,InstantiationException, IOException
{
    downloadProperties();
    Field[] fields = object.getClass().getDeclaredFields();
    for(Field field:fields)
    {
        if(field.getAnnotation(AutoInjectable.class)!=null)
        {
            field.setAccessible(true);
            String fieldClassName = field.getType().toString().split(" ")[1];
            String injectedClassName = properties.getProperty(fieldClassName);
            Object value = Class.forName( injectedClassName).newInstance ( );
            field.set(object, value);
        }
    }
    return object;
}
}


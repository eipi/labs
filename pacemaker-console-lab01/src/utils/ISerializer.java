package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by naysayer on 05/02/2016.
 */
public interface ISerializer {

    public static ISerializer getCustomSerializer(final String serializerPrefix, final String dataStore) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class foundClass = null;
        String serializerPrefixUpper = serializerPrefix.toUpperCase();
        try {
            foundClass = classLoader.loadClass("utils." + serializerPrefixUpper + "Serializer");
            if (foundClass != null) {
                Constructor<Object> constructor = foundClass.getDeclaredConstructors()[0];
                Object obj = constructor.newInstance(dataStore);
                if (obj instanceof ISerializer) {
                    return (ISerializer) obj;
                } else throw new ClassNotFoundException("Requested class has incorrect type." + obj.getClass());
            }
        } catch (ClassNotFoundException cndEx) {
            cndEx.printStackTrace();
        } catch (InstantiationException iEx) {
            iEx.printStackTrace();
        } catch (IllegalAccessException iaEx) {
            iaEx.printStackTrace();
        } catch (InvocationTargetException itEx) {
            itEx.printStackTrace();
        }
        return null;
    }

    public void push(Object o);

    public Object pop();

    public void read() throws Exception;

    public void write() throws Exception;

}

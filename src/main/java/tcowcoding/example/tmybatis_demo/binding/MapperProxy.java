package tcowcoding.example.tmybatis_demo.binding;

import org.springframework.core.serializer.Serializer;
import tcowcoding.example.tmybatis_demo.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private SqlSession sqlsession;

    private Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlsession, Class<T> mapperInterface) {
        this.sqlsession = sqlsession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return sqlsession.selecetOne(method.getName());        }
    }
}

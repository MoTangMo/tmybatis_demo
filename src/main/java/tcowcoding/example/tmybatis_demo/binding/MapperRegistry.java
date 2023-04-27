package tcowcoding.example.tmybatis_demo.binding;

import cn.hutool.core.lang.ClassScanner;
import tcowcoding.example.tmybatis_demo.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {

    //对扫描到的mapper 采用knownMapper进行存储，需要的时候直接就可以从该处取
    private final Map<Class<?> ,MapperProxyFactory<?>> konwnMapper = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlsession){
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) konwnMapper.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlsession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }

    }

    public <T> boolean hasMapper(Class<T> type){
        if (konwnMapper.containsKey(type)){
            return true;
        }
        return false;
    }

    public <T> void addMapper(Class<T> type){
        /* Mapper 必须是接口才会注册 */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            // 注册映射器代理工厂
            konwnMapper.put(type, new MapperProxyFactory<>(type));
        }
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}



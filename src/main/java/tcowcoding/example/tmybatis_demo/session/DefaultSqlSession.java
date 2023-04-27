package tcowcoding.example.tmybatis_demo.session;

import tcowcoding.example.tmybatis_demo.binding.MapperRegistry;

public class DefaultSqlSession implements SqlSession{

    private MapperRegistry registry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.registry = mapperRegistry;
    }


    @Override
    public <T> T selecetOne(String statement) {
        return (T)("你被代理了！" + "方法：" + statement );
    }

    @Override
    public <T> T selecetOneById(String statement, Object id) {
        return (T)("你被代理了！" + "方法：" + statement  + "参数为：" + id );
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return registry.getMapper(type,this);
    }
}

package tcowcoding.example.tmybatis_demo.session;

import tcowcoding.example.tmybatis_demo.binding.MapperRegistry;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private final MapperRegistry registry;

    public DefaultSqlSessionFactory(MapperRegistry registry) {
        this.registry = registry;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(registry);
    }
}

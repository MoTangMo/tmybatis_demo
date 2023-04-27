package tcowcoding.example.tmybatis_demo.session;

//定义部分SQL 接口
public interface SqlSession {

    <T> T selecetOne(String statement);


    <T> T selecetOneById(String statement,Object id);


    <T> T getMapper(Class<T> type);
}

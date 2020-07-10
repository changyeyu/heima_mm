package com.itheima.mm.util;

import com.itheima.mm.factory.MapperFactory;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * 获取动态代理对象工具类
 */

public class DaoInstanceUtil {
    /**
     * @param clazz 被代理对象的class
     * @param <T>   被代理对象
     * @return 返回代理对象
     */
    public static <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(DaoInstanceUtil.class.getClassLoader(),
                new Class[]{clazz},
                (proxy, method, args) -> {
                    SqlSession sqlSession = null;
                    try {
                        sqlSession = MapperFactory.getSqlSession(false);
                        T mapper = sqlSession.getMapper(clazz);
                        Object o = method.invoke(mapper, args);
                        sqlSession.commit();
                        return o;
                    } catch (Exception e) {
                        sqlSession.rollback();
                        throw new RuntimeException(e);
                    } finally {
                        if (sqlSession != null)
                            sqlSession.close();
                    }
                });
    }
}

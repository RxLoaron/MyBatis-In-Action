/**
 * Copyright 2009-2017 the original author or authors.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License.
 */
package mybatisdemo.hellomybatis;

import java.io.IOException;
import java.io.InputStream;
import mybatisdemo.hellomybatis.beans.User;
import mybatisdemo.hellomybatis.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * Created by lyp3314@gmail.com on 2017/8/31.
 */

/**
 * 1、接口式编程 原生： Dao ====> DaoImpl mybatis： Mapper ====> xxMapper.xml
 *
 * <p>2、SqlSession代表和数据库的一次会话；用完必须关闭； 3、SqlSession和connection一样都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。 （将接口和xml进行绑定） 5、两个重要的配置文件：
 * mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息 sql映射文件：保存了每一个sql语句的映射信息： 将sql抽取出来。
 */
public class TestUser {
    
    @Test
    public void addUser() {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        
        try {
            UserDao dao = sqlSession.getMapper(UserDao.class);
            User user = new User();
            user.setEmail("lyp3314@gmail.com");
            user.setUserName("Luoyp3");
            user.setSex(0);
            user.setDeptId(1);
            
            System.out.println("before add : " + user);
            dao.addUser(user);
            System.out.println("after add : " + user);
        }
        finally {
            sqlSession.close();
        }
    }
    
    @Test
    public void getUserById() {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        
        try {
            UserDao dao = sqlSession.getMapper(UserDao.class);
            // User user = dao.getUserById(4);
            //  User user = dao.getUserWithDeptById(4);
            //   User user = dao.getUserByDeptId(2);
            User user = dao.getUserByIdAndSex(9, 0);
            System.out.println("after add : " + user);
        }
        finally {
            sqlSession.close();
        }
    }
    
    private SqlSessionFactory getSqlSessionFactory() {
        String resource = "resources/mybatis_config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            return new SqlSessionFactoryBuilder().build(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

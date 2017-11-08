/**
 *    Copyright 2009-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package mybatisdemo.hellomybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import mybatisdemo.hellomybatis.beans.Department;
import mybatisdemo.hellomybatis.dao.DeptDao;

/**
 * Created by lyp3314@gmail.com on 2017/9/6.
 */

public class TestDept {
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

    @Test
    public void getDeptById() {

        SqlSession sqlSession = getSqlSessionFactory().openSession(true);

        try {
            DeptDao dao = sqlSession.getMapper(DeptDao.class);
            Department department = dao.getDeptById(1);

            System.out.println(department);
        }
        finally {
            sqlSession.close();
        }
    }
}

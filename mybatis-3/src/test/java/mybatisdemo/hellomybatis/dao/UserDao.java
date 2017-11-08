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
package mybatisdemo.hellomybatis.dao;

import org.apache.ibatis.annotations.Param;

import mybatisdemo.hellomybatis.beans.User;

/**
 * Created by lyp3314@gmail.com on 2017/8/31.
 */

public interface UserDao {
    public long addUser(User user);

    public User getUserById(Integer userId);

    public User getUserByDeptId(Integer deptid);

    public User getUserByIdAndSex(@Param("uid") Integer uid, @Param("sex") Integer sex);

    public User getUserWithDeptById(Integer userId);
}

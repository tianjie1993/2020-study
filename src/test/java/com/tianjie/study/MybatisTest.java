package com.tianjie.study;

import com.tianjie.study.y2020.mysql.TUser;
import com.tianjie.study.y2020.mysql.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;


    /**
     *  这里体现了mybatis 以及缓存的一个坑。
     *  如果在同一个session中查询一个值。然后修改了数据库中的值（这里利用线程睡眠时间手动在数据库修改）
     *  第二次查询的值不会变。因为这时他是从mybatis 的一级缓存 localCache中获取的
     *
     * @author tian.jie
     * @email tian.jie@rongzer.com
     * @date 2020-06-14 22:45
     * @return
     */
//    @Test
    @Transactional
    public void cacheTest() throws InterruptedException {
        TUser user = userMapper.getById(1);
        System.out.println(user);
        Thread.sleep(10000L);
        user =  user = userMapper.getById(1);
        System.out.println(user);
    }
}

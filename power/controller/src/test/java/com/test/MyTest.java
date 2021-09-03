package com.test;

import com.mayufeng.Application;
import com.mayufeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class MyTest {
//    @Autowired
    private UserService userService;

//    @Test
    public void myTest(){
        System.out.println(1111);
    }
}

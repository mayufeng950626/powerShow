package com.mayufeng.controller;

import com.mayufeng.pojo.User;
import com.mayufeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;
//    @GetMapping("/getUser")
//    public User hello(int id){
//        ExecutorService  executorService = Executors.newCachedThreadPool();
//        for(int i = 0 ; i< 10000; i++){
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("先返回了");
//                }
//            });
//        }
//
//        return  userService.getUser(id);
//    }
@GetMapping("/getUser")
public String hello(int id){
    ExecutorService  executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i< 10000; i++) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
            }
        });

    return  "OK";
}

}

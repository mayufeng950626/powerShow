package com.mayufeng.service;

import com.mayufeng.pojo.User;

public interface UserService {
    /**
     * 判断用户名是否已存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 注册创建用户信息
     * @param user
     */
    public User createUser(User user);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    public User queryUserForLogin(String username, String password);
}

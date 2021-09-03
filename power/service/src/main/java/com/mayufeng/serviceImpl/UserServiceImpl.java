package com.mayufeng.serviceImpl;

import com.mayufeng.common.MD5Utils;
import com.mayufeng.mapper.UserMapper;
import com.mayufeng.pojo.User;
import com.mayufeng.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 判断用户名是否已存在
     *
     * @param username
     * @return
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(User.class);
        Example.Criteria userExampleCriteria=userExample.createCriteria();
        userExampleCriteria.andEqualTo("username",username);
        User result = userMapper.selectOneByExample(userExample);
        // 判断查询结果是否为空
        return result==null?false:true;
    }

    /**
     * 注册创建用户信息
     *
     * @param user
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public User createUser(User user) {
        try {
            String md5Password = MD5Utils.getMD5Str(user.getPassword());
            user.setPassword(md5Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 创建的时候默认昵称就是账号
        user.setNickname(user.getUsername());
        user.setFace("");
        userMapper.insert(user);
        return null;
    }

    /**
     * 检索用户名和密码是否匹配，用于登录
     *
     * @param username
     * @param password
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public  User queryUserForLogin(String username, String password) {

//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Example userExample = new Example(User.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);

        User result = userMapper.selectOneByExample(userExample);

        return result;
    }

}

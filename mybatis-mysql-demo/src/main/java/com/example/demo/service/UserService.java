package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class UserService {


    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;

    @Autowired
    private UserDao userDao;

    /**
     * 通过name查找user
     * @param name
     * @return
     */
    public User getUserByName(String name){
        //通过name查找
        User user = userDao.findByName(name);
        return user;
    }

    /**
     * 查找所有User
     * @return
     */
    public List<User> getUAllUsers(){
        //查找全部
        List<User> userList = userDao.findAll();
        return userList;
    }

    /**
     * 新增一条user
     * @param user
     * @return
     */
    public int insertUser(User user) {
        return userDao.save(user);
    }

    /**
     * 删除一条user
     * @param name
     * @return
     */
    public int deleteUser(String name) {
        return userDao.deleteByname(name);
    }


    /**
     * update所有user年龄加一（使用注解事务）
     * 当抛出指定的异常类型时，事务回滚
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int ageAddOne() throws Exception {
        int count = 0;
        List<User> userList= getUAllUsers();
        for(User user : userList){
            user.setAge(user.getAge()+1);
            count+=userDao.updateAge(user);
        }

        //count = count/0; //抛出异常测试
        return count;
    }

    /**
     * update所有user年龄减一(TransactionStatus事务)
     * @return
     */
    public int ageSubOne(){
        // 手动开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {

            int count = 0;
            List<User> userList = getUAllUsers();
            for (User user : userList) {
                user.setAge(user.getAge() - 1);
                count += userDao.updateAge(user);
            }

            //count = count/0; //抛出异常测试

            // 手动提交事务
            dataSourceTransactionManager.commit(transactionStatus);
            return count;
        }
        catch(Exception ex){
            log.error(ex.toString());
            // 手动回滚事务
            dataSourceTransactionManager.rollback(transactionStatus);
            return -1;
        }
    }
}

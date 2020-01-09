package com.xjt.service.impl;

import javax.annotation.Resource;

import com.xjt.dao.IUserDao;
import com.xjt.model.*;
import com.xjt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liqing
 * @version 1.0
 * @date 2019-12-25 14:41
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public List<User> login(User user) {
        return userDao.login(user);
    }

    @Override
    public int logon(LogonBean userLogon) {
        return userDao.logon(userLogon);
    }

    @Override
    public List<CategoryBean> getCategory(CategoryBean categoryBean){
        return  userDao.getCategory(categoryBean);
    }

    @Override
    public int insertCategory(CategoryBean categoryBean){
        return userDao.insertCategory(categoryBean);
    }

    @Override
    public int deleteCategory(CategoryBean categoryBean){
        return userDao.deleteCategory(categoryBean);
    }

    @Override
    public int updateCategory(CategoryBean categoryBean){
        return userDao.updateCategory(categoryBean);
    }

    @Override
    public  int recordBill(RecordBean recordBean){
        return userDao.recordBill(recordBean);
    }

    @Override
    public List<SpendBean>getSpendSituation(SpendBean spendBean){
        return userDao.getSpendSituation(spendBean);
    }

    @Override
    public List<TrendBean>getSpendTrend(TrendBean trendBean){
        return userDao.getSpendTrend(trendBean);
    }



}

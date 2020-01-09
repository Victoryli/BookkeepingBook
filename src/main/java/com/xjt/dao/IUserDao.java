package com.xjt.dao;

import com.xjt.model.*;

import java.util.List;

/**
 * @author liqing
 * @version 1.0
 * @date 2019-12-25 14:51
 */
public interface IUserDao {
    //User login(@Param("username") String username, @Param("password") String password);
    List<User> login(User user);

    //int logon(@Param("email") String email, @Param("username") String username, @Param("role") String role, @Param("mobile") String mobile, @Param("password") String password);
    int logon(LogonBean userLogon);
    //获取当前用户所有消费类型
    List<CategoryBean> getCategory(CategoryBean categoryBean);
    //插入新的消费类型
    int insertCategory(CategoryBean categoryBean);

    //删除单个消费类型
    int deleteCategory(CategoryBean categoryBean);

    //更新单个消费类型名称
    int updateCategory(CategoryBean categoryBean);

    //插入单条消费记录
    int recordBill(RecordBean recordBean);

    //查询本月花费情况
    List<SpendBean> getSpendSituation(SpendBean spendBean);

    //查询本月、上月、前三个月的花费趋势
    List<TrendBean> getSpendTrend(TrendBean trendBean);




}

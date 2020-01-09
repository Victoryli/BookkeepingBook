package com.xjt.service;

import com.xjt.model.*;

import java.util.List;

/**
 * @author liqing
 * @version 1.0
 * @date 2019-12-25 14:41
 */
public interface IUserService {
    List<User> login(User user);

    int logon(LogonBean userLogon);

    int insertCategory(CategoryBean categoryBean);

    List<CategoryBean> getCategory(CategoryBean categoryBean);

    int deleteCategory(CategoryBean categoryBean);

    int updateCategory(CategoryBean categoryBean);

    int recordBill(RecordBean recordBean);

    List<SpendBean>getSpendSituation(SpendBean spendBean);

    List<TrendBean>getSpendTrend(TrendBean trendBean);

}

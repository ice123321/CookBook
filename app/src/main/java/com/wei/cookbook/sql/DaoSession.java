package com.wei.cookbook.sql;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.wei.cookbook.model.FoodBean;
import com.wei.cookbook.model.UserBean;

import com.wei.cookbook.sql.FoodBeanDao;
import com.wei.cookbook.sql.UserBeanDao;


public class DaoSession extends AbstractDaoSession {

    private final DaoConfig foodBeanDaoConfig;
    private final DaoConfig userBeanDaoConfig;

    private final FoodBeanDao foodBeanDao;
    private final UserBeanDao userBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        foodBeanDaoConfig = daoConfigMap.get(FoodBeanDao.class).clone();
        foodBeanDaoConfig.initIdentityScope(type);

        userBeanDaoConfig = daoConfigMap.get(UserBeanDao.class).clone();
        userBeanDaoConfig.initIdentityScope(type);

        foodBeanDao = new FoodBeanDao(foodBeanDaoConfig, this);
        userBeanDao = new UserBeanDao(userBeanDaoConfig, this);

        registerDao(FoodBean.class, foodBeanDao);
        registerDao(UserBean.class, userBeanDao);
    }
    
    public void clear() {
        foodBeanDaoConfig.clearIdentityScope();
        userBeanDaoConfig.clearIdentityScope();
    }

    public FoodBeanDao getFoodBeanDao() {
        return foodBeanDao;
    }

    public UserBeanDao getUserBeanDao() {
        return userBeanDao;
    }

}

package com.yuda.coolweather.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.yuda.coolweather.db.City;
import com.yuda.coolweather.db.County;
import com.yuda.coolweather.db.Provice;

import com.yuda.coolweather.db.CityDao;
import com.yuda.coolweather.db.CountyDao;
import com.yuda.coolweather.db.ProviceDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cityDaoConfig;
    private final DaoConfig countyDaoConfig;
    private final DaoConfig proviceDaoConfig;

    private final CityDao cityDao;
    private final CountyDao countyDao;
    private final ProviceDao proviceDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cityDaoConfig = daoConfigMap.get(CityDao.class).clone();
        cityDaoConfig.initIdentityScope(type);

        countyDaoConfig = daoConfigMap.get(CountyDao.class).clone();
        countyDaoConfig.initIdentityScope(type);

        proviceDaoConfig = daoConfigMap.get(ProviceDao.class).clone();
        proviceDaoConfig.initIdentityScope(type);

        cityDao = new CityDao(cityDaoConfig, this);
        countyDao = new CountyDao(countyDaoConfig, this);
        proviceDao = new ProviceDao(proviceDaoConfig, this);

        registerDao(City.class, cityDao);
        registerDao(County.class, countyDao);
        registerDao(Provice.class, proviceDao);
    }
    
    public void clear() {
        cityDaoConfig.clearIdentityScope();
        countyDaoConfig.clearIdentityScope();
        proviceDaoConfig.clearIdentityScope();
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public CountyDao getCountyDao() {
        return countyDao;
    }

    public ProviceDao getProviceDao() {
        return proviceDao;
    }

}
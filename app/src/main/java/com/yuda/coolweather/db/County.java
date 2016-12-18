package com.yuda.coolweather.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by yuda on 2016/12/17
 */
@Entity(
        active = true,
        nameInDb = "CountyBean"
)
public class County {
    @Id(autoincrement = true)
    private Long id;
    private String countyName;
    private String weatherId;
    private int cityId;
/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;
/** Used for active entity operations. */
@Generated(hash = 1199581902)
private transient CountyDao myDao;
@Generated(hash = 1088402961)
public County(Long id, String countyName, String weatherId, int cityId) {
    this.id = id;
    this.countyName = countyName;
    this.weatherId = weatherId;
    this.cityId = cityId;
}
@Generated(hash = 1991272252)
public County() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getCountyName() {
    return this.countyName;
}
public void setCountyName(String countyName) {
    this.countyName = countyName;
}
public String getWeatherId() {
    return this.weatherId;
}
public void setWeatherId(String weatherId) {
    this.weatherId = weatherId;
}
public int getCityId() {
    return this.cityId;
}
public void setCityId(int cityId) {
    this.cityId = cityId;
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}
/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1951788226)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getCountyDao() : null;
}
}

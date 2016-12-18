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
        nameInDb = "CityBean"
)
public class City {
    @Id(autoincrement = true)
    private Long id;
    private String cityName;
    private int cityCode;
    private int proviceId;
/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;
/** Used for active entity operations. */
@Generated(hash = 448079911)
private transient CityDao myDao;
@Generated(hash = 362387143)
public City(Long id, String cityName, int cityCode, int proviceId) {
    this.id = id;
    this.cityName = cityName;
    this.cityCode = cityCode;
    this.proviceId = proviceId;
}
@Generated(hash = 750791287)
public City() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getCityName() {
    return this.cityName;
}
public void setCityName(String cityName) {
    this.cityName = cityName;
}
public int getCityCode() {
    return this.cityCode;
}
public void setCityCode(int cityCode) {
    this.cityCode = cityCode;
}
public int getProviceId() {
    return this.proviceId;
}
public void setProviceId(int proviceId) {
    this.proviceId = proviceId;
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
@Generated(hash = 293508440)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getCityDao() : null;
}
}

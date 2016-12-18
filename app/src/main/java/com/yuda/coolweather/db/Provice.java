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
        nameInDb = "ProviceBean"
)
public class Provice {
    @Id(autoincrement = true)
    private Long id;
    private String provinceName;
    private int provinceCode;
/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;
/** Used for active entity operations. */
@Generated(hash = 1349816787)
private transient ProviceDao myDao;
@Generated(hash = 344145723)
public Provice(Long id, String provinceName, int provinceCode) {
    this.id = id;
    this.provinceName = provinceName;
    this.provinceCode = provinceCode;
}
@Generated(hash = 1489674795)
public Provice() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getProvinceName() {
    return this.provinceName;
}
public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
}
public int getProvinceCode() {
    return this.provinceCode;
}
public void setProvinceCode(int provinceCode) {
    this.provinceCode = provinceCode;
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
@Generated(hash = 1579385168)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getProviceDao() : null;
}
}

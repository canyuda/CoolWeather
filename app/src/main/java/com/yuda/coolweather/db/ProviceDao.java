package com.yuda.coolweather.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ProviceBean".
*/
public class ProviceDao extends AbstractDao<Provice, Long> {

    public static final String TABLENAME = "ProviceBean";

    /**
     * Properties of entity Provice.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ProvinceName = new Property(1, String.class, "provinceName", false, "PROVINCE_NAME");
        public final static Property ProvinceCode = new Property(2, int.class, "provinceCode", false, "PROVINCE_CODE");
    }

    private DaoSession daoSession;


    public ProviceDao(DaoConfig config) {
        super(config);
    }
    
    public ProviceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ProviceBean\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PROVINCE_NAME\" TEXT," + // 1: provinceName
                "\"PROVINCE_CODE\" INTEGER NOT NULL );"); // 2: provinceCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ProviceBean\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Provice entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(2, provinceName);
        }
        stmt.bindLong(3, entity.getProvinceCode());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Provice entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(2, provinceName);
        }
        stmt.bindLong(3, entity.getProvinceCode());
    }

    @Override
    protected final void attachEntity(Provice entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Provice readEntity(Cursor cursor, int offset) {
        Provice entity = new Provice( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // provinceName
            cursor.getInt(offset + 2) // provinceCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Provice entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProvinceName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setProvinceCode(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Provice entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Provice entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Provice entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
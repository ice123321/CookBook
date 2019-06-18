package com.wei.cookbook.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.wei.cookbook.model.UserBean;

public class UserBeanDao extends AbstractDao<UserBean, String> {

    public static final String TABLENAME = "USER_BEAN";

    public static class Properties {
        public final static Property Account = new Property(0, String.class, "account", true, "ACCOUNT");
        public final static Property PassWord = new Property(1, String.class, "passWord", false, "PASS_WORD");
    }


    public UserBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UserBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }


    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_BEAN\" (" +
                "\"ACCOUNT\" TEXT PRIMARY KEY NOT NULL ," +
                "\"PASS_WORD\" TEXT);");
    }


    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserBean entity) {
        stmt.clearBindings();
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(1, account);
        }
 
        String passWord = entity.getPassWord();
        if (passWord != null) {
            stmt.bindString(2, passWord);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserBean entity) {
        stmt.clearBindings();
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(1, account);
        }
 
        String passWord = entity.getPassWord();
        if (passWord != null) {
            stmt.bindString(2, passWord);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public UserBean readEntity(Cursor cursor, int offset) {
        UserBean entity = new UserBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0),
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1)
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserBean entity, int offset) {
        entity.setAccount(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setPassWord(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final String updateKeyAfterInsert(UserBean entity, long rowId) {
        return entity.getAccount();
    }
    
    @Override
    public String getKey(UserBean entity) {
        if(entity != null) {
            return entity.getAccount();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserBean entity) {
        return entity.getAccount() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

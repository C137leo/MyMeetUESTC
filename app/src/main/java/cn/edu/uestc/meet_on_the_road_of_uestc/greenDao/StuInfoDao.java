package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.StuInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "STU_INFO".
*/
public class StuInfoDao extends AbstractDao<StuInfo, String> {

    public static final String TABLENAME = "STU_INFO";

    /**
     * Properties of entity StuInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property StuID = new Property(0, String.class, "StuID", true, "STU_ID");
        public final static Property StuName = new Property(1, String.class, "StuName", false, "STU_NAME");
        public final static Property StuPassWord = new Property(2, String.class, "StuPassWord", false, "STU_PASS_WORD");
        public final static Property StuSignature = new Property(3, String.class, "StuSignature", false, "STU_SIGNATURE");
        public final static Property StuGrade = new Property(4, int.class, "StuGrade", false, "STU_GRADE");
        public final static Property NickName = new Property(5, String.class, "NickName", false, "NICK_NAME");
    }


    public StuInfoDao(DaoConfig config) {
        super(config);
    }
    
    public StuInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STU_INFO\" (" + //
                "\"STU_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: StuID
                "\"STU_NAME\" TEXT," + // 1: StuName
                "\"STU_PASS_WORD\" TEXT," + // 2: StuPassWord
                "\"STU_SIGNATURE\" TEXT," + // 3: StuSignature
                "\"STU_GRADE\" INTEGER NOT NULL ," + // 4: StuGrade
                "\"NICK_NAME\" TEXT);"); // 5: NickName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STU_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, StuInfo entity) {
        stmt.clearBindings();
 
        String StuID = entity.getStuID();
        if (StuID != null) {
            stmt.bindString(1, StuID);
        }
 
        String StuName = entity.getStuName();
        if (StuName != null) {
            stmt.bindString(2, StuName);
        }
 
        String StuPassWord = entity.getStuPassWord();
        if (StuPassWord != null) {
            stmt.bindString(3, StuPassWord);
        }
 
        String StuSignature = entity.getStuSignature();
        if (StuSignature != null) {
            stmt.bindString(4, StuSignature);
        }
        stmt.bindLong(5, entity.getStuGrade());
 
        String NickName = entity.getNickName();
        if (NickName != null) {
            stmt.bindString(6, NickName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, StuInfo entity) {
        stmt.clearBindings();
 
        String StuID = entity.getStuID();
        if (StuID != null) {
            stmt.bindString(1, StuID);
        }
 
        String StuName = entity.getStuName();
        if (StuName != null) {
            stmt.bindString(2, StuName);
        }
 
        String StuPassWord = entity.getStuPassWord();
        if (StuPassWord != null) {
            stmt.bindString(3, StuPassWord);
        }
 
        String StuSignature = entity.getStuSignature();
        if (StuSignature != null) {
            stmt.bindString(4, StuSignature);
        }
        stmt.bindLong(5, entity.getStuGrade());
 
        String NickName = entity.getNickName();
        if (NickName != null) {
            stmt.bindString(6, NickName);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public StuInfo readEntity(Cursor cursor, int offset) {
        StuInfo entity = new StuInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // StuID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // StuName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // StuPassWord
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // StuSignature
            cursor.getInt(offset + 4), // StuGrade
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // NickName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, StuInfo entity, int offset) {
        entity.setStuID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStuName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStuPassWord(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStuSignature(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setStuGrade(cursor.getInt(offset + 4));
        entity.setNickName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final String updateKeyAfterInsert(StuInfo entity, long rowId) {
        return entity.getStuID();
    }
    
    @Override
    public String getKey(StuInfo entity) {
        if(entity != null) {
            return entity.getStuID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(StuInfo entity) {
        return entity.getStuID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

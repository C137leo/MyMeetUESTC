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
public class StuInfoDao extends AbstractDao<StuInfo, Long> {

    public static final String TABLENAME = "STU_INFO";

    /**
     * Properties of entity StuInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property StuID = new Property(0, Long.class, "StuID", true, "_id");
        public final static Property StuName = new Property(1, String.class, "StuName", false, "STU_NAME");
        public final static Property StuPassWord = new Property(2, String.class, "StuPassWord", false, "STU_PASS_WORD");
        public final static Property StuSignature = new Property(3, String.class, "StuSignature", false, "STU_SIGNATURE");
        public final static Property StuGrade = new Property(4, int.class, "StuGrade", false, "STU_GRADE");
        public final static Property IsRemember = new Property(5, boolean.class, "isRemember", false, "IS_REMEMBER");
    }

    private DaoSession daoSession;


    public StuInfoDao(DaoConfig config) {
        super(config);
    }
    
    public StuInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STU_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: StuID
                "\"STU_NAME\" TEXT," + // 1: StuName
                "\"STU_PASS_WORD\" TEXT," + // 2: StuPassWord
                "\"STU_SIGNATURE\" TEXT," + // 3: StuSignature
                "\"STU_GRADE\" INTEGER NOT NULL ," + // 4: StuGrade
                "\"IS_REMEMBER\" INTEGER NOT NULL );"); // 5: isRemember
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STU_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, StuInfo entity) {
        stmt.clearBindings();
 
        Long StuID = entity.getStuID();
        if (StuID != null) {
            stmt.bindLong(1, StuID);
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
        stmt.bindLong(6, entity.getIsRemember() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, StuInfo entity) {
        stmt.clearBindings();
 
        Long StuID = entity.getStuID();
        if (StuID != null) {
            stmt.bindLong(1, StuID);
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
        stmt.bindLong(6, entity.getIsRemember() ? 1L: 0L);
    }

    @Override
    protected final void attachEntity(StuInfo entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public StuInfo readEntity(Cursor cursor, int offset) {
        StuInfo entity = new StuInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // StuID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // StuName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // StuPassWord
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // StuSignature
            cursor.getInt(offset + 4), // StuGrade
            cursor.getShort(offset + 5) != 0 // isRemember
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, StuInfo entity, int offset) {
        entity.setStuID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStuName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStuPassWord(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStuSignature(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setStuGrade(cursor.getInt(offset + 4));
        entity.setIsRemember(cursor.getShort(offset + 5) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(StuInfo entity, long rowId) {
        entity.setStuID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(StuInfo entity) {
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

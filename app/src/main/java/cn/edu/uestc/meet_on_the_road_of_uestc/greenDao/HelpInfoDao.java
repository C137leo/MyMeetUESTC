package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.HelpInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HELP_INFO".
*/
public class HelpInfoDao extends AbstractDao<HelpInfo, String> {

    public static final String TABLENAME = "HELP_INFO";

    /**
     * Properties of entity HelpInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UID = new Property(0, String.class, "UID", true, "UID");
        public final static Property StuID = new Property(1, String.class, "StuID", false, "STU_ID");
        public final static Property Location = new Property(2, String.class, "location", false, "LOCATION");
        public final static Property Owner_name = new Property(3, String.class, "owner_name", false, "OWNER_NAME");
        public final static Property Good_title = new Property(4, String.class, "good_title", false, "GOOD_TITLE");
        public final static Property Publish_time = new Property(5, String.class, "publish_time", false, "PUBLISH_TIME");
        public final static Property IsPay = new Property(6, int.class, "isPay", false, "IS_PAY");
        public final static Property Good_detail = new Property(7, String.class, "good_detail", false, "GOOD_DETAIL");
        public final static Property IsFinish = new Property(8, int.class, "isFinish", false, "IS_FINISH");
        public final static Property WhoFinishIt = new Property(9, String.class, "whoFinishIt", false, "WHO_FINISH_IT");
        public final static Property AcceptTime = new Property(10, String.class, "acceptTime", false, "ACCEPT_TIME");
        public final static Property WhoFinishItStuID = new Property(11, String.class, "whoFinishItStuID", false, "WHO_FINISH_IT_STU_ID");
        public final static Property WhoFinishItStuMajor = new Property(12, String.class, "whoFinishItStuMajor", false, "WHO_FINISH_IT_STU_MAJOR");
        public final static Property WhoFinishItStuGrade = new Property(13, int.class, "whoFinishItStuGrade", false, "WHO_FINISH_IT_STU_GRADE");
        public final static Property Latitude = new Property(14, double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(15, double.class, "longitude", false, "LONGITUDE");
    }


    public HelpInfoDao(DaoConfig config) {
        super(config);
    }
    
    public HelpInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HELP_INFO\" (" + //
                "\"UID\" TEXT PRIMARY KEY NOT NULL ," + // 0: UID
                "\"STU_ID\" TEXT," + // 1: StuID
                "\"LOCATION\" TEXT," + // 2: location
                "\"OWNER_NAME\" TEXT," + // 3: owner_name
                "\"GOOD_TITLE\" TEXT," + // 4: good_title
                "\"PUBLISH_TIME\" TEXT," + // 5: publish_time
                "\"IS_PAY\" INTEGER NOT NULL ," + // 6: isPay
                "\"GOOD_DETAIL\" TEXT," + // 7: good_detail
                "\"IS_FINISH\" INTEGER NOT NULL ," + // 8: isFinish
                "\"WHO_FINISH_IT\" TEXT," + // 9: whoFinishIt
                "\"ACCEPT_TIME\" TEXT," + // 10: acceptTime
                "\"WHO_FINISH_IT_STU_ID\" TEXT," + // 11: whoFinishItStuID
                "\"WHO_FINISH_IT_STU_MAJOR\" TEXT," + // 12: whoFinishItStuMajor
                "\"WHO_FINISH_IT_STU_GRADE\" INTEGER NOT NULL ," + // 13: whoFinishItStuGrade
                "\"LATITUDE\" REAL NOT NULL ," + // 14: latitude
                "\"LONGITUDE\" REAL NOT NULL );"); // 15: longitude
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HELP_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HelpInfo entity) {
        stmt.clearBindings();
 
        String UID = entity.getUID();
        if (UID != null) {
            stmt.bindString(1, UID);
        }
 
        String StuID = entity.getStuID();
        if (StuID != null) {
            stmt.bindString(2, StuID);
        }
 
        String location = entity.getLocation();
        if (location != null) {
            stmt.bindString(3, location);
        }
 
        String owner_name = entity.getOwner_name();
        if (owner_name != null) {
            stmt.bindString(4, owner_name);
        }
 
        String good_title = entity.getGood_title();
        if (good_title != null) {
            stmt.bindString(5, good_title);
        }
 
        String publish_time = entity.getPublish_time();
        if (publish_time != null) {
            stmt.bindString(6, publish_time);
        }
        stmt.bindLong(7, entity.getIsPay());
 
        String good_detail = entity.getGood_detail();
        if (good_detail != null) {
            stmt.bindString(8, good_detail);
        }
        stmt.bindLong(9, entity.getIsFinish());
 
        String whoFinishIt = entity.getWhoFinishIt();
        if (whoFinishIt != null) {
            stmt.bindString(10, whoFinishIt);
        }
 
        String acceptTime = entity.getAcceptTime();
        if (acceptTime != null) {
            stmt.bindString(11, acceptTime);
        }
 
        String whoFinishItStuID = entity.getWhoFinishItStuID();
        if (whoFinishItStuID != null) {
            stmt.bindString(12, whoFinishItStuID);
        }
 
        String whoFinishItStuMajor = entity.getWhoFinishItStuMajor();
        if (whoFinishItStuMajor != null) {
            stmt.bindString(13, whoFinishItStuMajor);
        }
        stmt.bindLong(14, entity.getWhoFinishItStuGrade());
        stmt.bindDouble(15, entity.getLatitude());
        stmt.bindDouble(16, entity.getLongitude());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HelpInfo entity) {
        stmt.clearBindings();
 
        String UID = entity.getUID();
        if (UID != null) {
            stmt.bindString(1, UID);
        }
 
        String StuID = entity.getStuID();
        if (StuID != null) {
            stmt.bindString(2, StuID);
        }
 
        String location = entity.getLocation();
        if (location != null) {
            stmt.bindString(3, location);
        }
 
        String owner_name = entity.getOwner_name();
        if (owner_name != null) {
            stmt.bindString(4, owner_name);
        }
 
        String good_title = entity.getGood_title();
        if (good_title != null) {
            stmt.bindString(5, good_title);
        }
 
        String publish_time = entity.getPublish_time();
        if (publish_time != null) {
            stmt.bindString(6, publish_time);
        }
        stmt.bindLong(7, entity.getIsPay());
 
        String good_detail = entity.getGood_detail();
        if (good_detail != null) {
            stmt.bindString(8, good_detail);
        }
        stmt.bindLong(9, entity.getIsFinish());
 
        String whoFinishIt = entity.getWhoFinishIt();
        if (whoFinishIt != null) {
            stmt.bindString(10, whoFinishIt);
        }
 
        String acceptTime = entity.getAcceptTime();
        if (acceptTime != null) {
            stmt.bindString(11, acceptTime);
        }
 
        String whoFinishItStuID = entity.getWhoFinishItStuID();
        if (whoFinishItStuID != null) {
            stmt.bindString(12, whoFinishItStuID);
        }
 
        String whoFinishItStuMajor = entity.getWhoFinishItStuMajor();
        if (whoFinishItStuMajor != null) {
            stmt.bindString(13, whoFinishItStuMajor);
        }
        stmt.bindLong(14, entity.getWhoFinishItStuGrade());
        stmt.bindDouble(15, entity.getLatitude());
        stmt.bindDouble(16, entity.getLongitude());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public HelpInfo readEntity(Cursor cursor, int offset) {
        HelpInfo entity = new HelpInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // UID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // StuID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // location
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // owner_name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // good_title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // publish_time
            cursor.getInt(offset + 6), // isPay
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // good_detail
            cursor.getInt(offset + 8), // isFinish
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // whoFinishIt
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // acceptTime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // whoFinishItStuID
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // whoFinishItStuMajor
            cursor.getInt(offset + 13), // whoFinishItStuGrade
            cursor.getDouble(offset + 14), // latitude
            cursor.getDouble(offset + 15) // longitude
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HelpInfo entity, int offset) {
        entity.setUID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStuID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLocation(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOwner_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGood_title(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPublish_time(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIsPay(cursor.getInt(offset + 6));
        entity.setGood_detail(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIsFinish(cursor.getInt(offset + 8));
        entity.setWhoFinishIt(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAcceptTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setWhoFinishItStuID(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setWhoFinishItStuMajor(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setWhoFinishItStuGrade(cursor.getInt(offset + 13));
        entity.setLatitude(cursor.getDouble(offset + 14));
        entity.setLongitude(cursor.getDouble(offset + 15));
     }
    
    @Override
    protected final String updateKeyAfterInsert(HelpInfo entity, long rowId) {
        return entity.getUID();
    }
    
    @Override
    public String getKey(HelpInfo entity) {
        if(entity != null) {
            return entity.getUID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HelpInfo entity) {
        return entity.getUID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

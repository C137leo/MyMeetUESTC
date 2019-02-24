package cn.edu.uestc.meet_on_the_road_of_uestc.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.edu.uestc.meet_on_the_road_of_uestc.converter.StuInfoListConverter;
import java.util.List;

import cn.edu.uestc.meet_on_the_road_of_uestc.greenDao.eneities.AppointmentInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "APPOINTMENT_INFO".
*/
public class AppointmentInfoDao extends AbstractDao<AppointmentInfo, String> {

    public static final String TABLENAME = "APPOINTMENT_INFO";

    /**
     * Properties of entity AppointmentInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property AppointmentUID = new Property(0, String.class, "appointmentUID", true, "APPOINTMENT_UID");
        public final static Property AppointmentTitle = new Property(1, String.class, "appointmentTitle", false, "APPOINTMENT_TITLE");
        public final static Property PublishTime = new Property(2, String.class, "publishTime", false, "PUBLISH_TIME");
        public final static Property WhoPublish = new Property(3, String.class, "whoPublish", false, "WHO_PUBLISH");
        public final static Property WhoPublishStuID = new Property(4, String.class, "whoPublishStuID", false, "WHO_PUBLISH_STU_ID");
        public final static Property WhoPublishStuGrade = new Property(5, int.class, "whoPublishStuGrade", false, "WHO_PUBLISH_STU_GRADE");
        public final static Property WhoPublishStuMajor = new Property(6, String.class, "whoPublishStuMajor", false, "WHO_PUBLISH_STU_MAJOR");
        public final static Property Location = new Property(7, String.class, "location", false, "LOCATION");
        public final static Property AppointmentTime = new Property(8, String.class, "appointmentTime", false, "APPOINTMENT_TIME");
        public final static Property AppointmentLatitude = new Property(9, double.class, "appointmentLatitude", false, "APPOINTMENT_LATITUDE");
        public final static Property AppointmentLongtitude = new Property(10, double.class, "appointmentLongtitude", false, "APPOINTMENT_LONGTITUDE");
        public final static Property AppointmentNum = new Property(11, int.class, "appointmentNum", false, "APPOINTMENT_NUM");
        public final static Property AppointmentType = new Property(12, int.class, "appointmentType", false, "APPOINTMENT_TYPE");
        public final static Property AppointmentTypeText = new Property(13, String.class, "appointmentTypeText", false, "APPOINTMENT_TYPE_TEXT");
        public final static Property AppointmentStatus = new Property(14, int.class, "appointmentStatus", false, "APPOINTMENT_STATUS");
        public final static Property AppointmentStuInfoList = new Property(15, String.class, "appointmentStuInfoList", false, "APPOINTMENT_STU_INFO_LIST");
    }

    private final StuInfoListConverter appointmentStuInfoListConverter = new StuInfoListConverter();

    public AppointmentInfoDao(DaoConfig config) {
        super(config);
    }
    
    public AppointmentInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"APPOINTMENT_INFO\" (" + //
                "\"APPOINTMENT_UID\" TEXT PRIMARY KEY NOT NULL ," + // 0: appointmentUID
                "\"APPOINTMENT_TITLE\" TEXT," + // 1: appointmentTitle
                "\"PUBLISH_TIME\" TEXT," + // 2: publishTime
                "\"WHO_PUBLISH\" TEXT," + // 3: whoPublish
                "\"WHO_PUBLISH_STU_ID\" TEXT," + // 4: whoPublishStuID
                "\"WHO_PUBLISH_STU_GRADE\" INTEGER NOT NULL ," + // 5: whoPublishStuGrade
                "\"WHO_PUBLISH_STU_MAJOR\" TEXT," + // 6: whoPublishStuMajor
                "\"LOCATION\" TEXT," + // 7: location
                "\"APPOINTMENT_TIME\" TEXT," + // 8: appointmentTime
                "\"APPOINTMENT_LATITUDE\" REAL NOT NULL ," + // 9: appointmentLatitude
                "\"APPOINTMENT_LONGTITUDE\" REAL NOT NULL ," + // 10: appointmentLongtitude
                "\"APPOINTMENT_NUM\" INTEGER NOT NULL ," + // 11: appointmentNum
                "\"APPOINTMENT_TYPE\" INTEGER NOT NULL ," + // 12: appointmentType
                "\"APPOINTMENT_TYPE_TEXT\" TEXT," + // 13: appointmentTypeText
                "\"APPOINTMENT_STATUS\" INTEGER NOT NULL ," + // 14: appointmentStatus
                "\"APPOINTMENT_STU_INFO_LIST\" TEXT);"); // 15: appointmentStuInfoList
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"APPOINTMENT_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AppointmentInfo entity) {
        stmt.clearBindings();
 
        String appointmentUID = entity.getAppointmentUID();
        if (appointmentUID != null) {
            stmt.bindString(1, appointmentUID);
        }
 
        String appointmentTitle = entity.getAppointmentTitle();
        if (appointmentTitle != null) {
            stmt.bindString(2, appointmentTitle);
        }
 
        String publishTime = entity.getPublishTime();
        if (publishTime != null) {
            stmt.bindString(3, publishTime);
        }
 
        String whoPublish = entity.getWhoPublish();
        if (whoPublish != null) {
            stmt.bindString(4, whoPublish);
        }
 
        String whoPublishStuID = entity.getWhoPublishStuID();
        if (whoPublishStuID != null) {
            stmt.bindString(5, whoPublishStuID);
        }
        stmt.bindLong(6, entity.getWhoPublishStuGrade());
 
        String whoPublishStuMajor = entity.getWhoPublishStuMajor();
        if (whoPublishStuMajor != null) {
            stmt.bindString(7, whoPublishStuMajor);
        }
 
        String location = entity.getLocation();
        if (location != null) {
            stmt.bindString(8, location);
        }
 
        String appointmentTime = entity.getAppointmentTime();
        if (appointmentTime != null) {
            stmt.bindString(9, appointmentTime);
        }
        stmt.bindDouble(10, entity.getAppointmentLatitude());
        stmt.bindDouble(11, entity.getAppointmentLongtitude());
        stmt.bindLong(12, entity.getAppointmentNum());
        stmt.bindLong(13, entity.getAppointmentType());
 
        String appointmentTypeText = entity.getAppointmentTypeText();
        if (appointmentTypeText != null) {
            stmt.bindString(14, appointmentTypeText);
        }
        stmt.bindLong(15, entity.getAppointmentStatus());
 
        List appointmentStuInfoList = entity.getAppointmentStuInfoList();
        if (appointmentStuInfoList != null) {
            stmt.bindString(16, appointmentStuInfoListConverter.convertToDatabaseValue(appointmentStuInfoList));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AppointmentInfo entity) {
        stmt.clearBindings();
 
        String appointmentUID = entity.getAppointmentUID();
        if (appointmentUID != null) {
            stmt.bindString(1, appointmentUID);
        }
 
        String appointmentTitle = entity.getAppointmentTitle();
        if (appointmentTitle != null) {
            stmt.bindString(2, appointmentTitle);
        }
 
        String publishTime = entity.getPublishTime();
        if (publishTime != null) {
            stmt.bindString(3, publishTime);
        }
 
        String whoPublish = entity.getWhoPublish();
        if (whoPublish != null) {
            stmt.bindString(4, whoPublish);
        }
 
        String whoPublishStuID = entity.getWhoPublishStuID();
        if (whoPublishStuID != null) {
            stmt.bindString(5, whoPublishStuID);
        }
        stmt.bindLong(6, entity.getWhoPublishStuGrade());
 
        String whoPublishStuMajor = entity.getWhoPublishStuMajor();
        if (whoPublishStuMajor != null) {
            stmt.bindString(7, whoPublishStuMajor);
        }
 
        String location = entity.getLocation();
        if (location != null) {
            stmt.bindString(8, location);
        }
 
        String appointmentTime = entity.getAppointmentTime();
        if (appointmentTime != null) {
            stmt.bindString(9, appointmentTime);
        }
        stmt.bindDouble(10, entity.getAppointmentLatitude());
        stmt.bindDouble(11, entity.getAppointmentLongtitude());
        stmt.bindLong(12, entity.getAppointmentNum());
        stmt.bindLong(13, entity.getAppointmentType());
 
        String appointmentTypeText = entity.getAppointmentTypeText();
        if (appointmentTypeText != null) {
            stmt.bindString(14, appointmentTypeText);
        }
        stmt.bindLong(15, entity.getAppointmentStatus());
 
        List appointmentStuInfoList = entity.getAppointmentStuInfoList();
        if (appointmentStuInfoList != null) {
            stmt.bindString(16, appointmentStuInfoListConverter.convertToDatabaseValue(appointmentStuInfoList));
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public AppointmentInfo readEntity(Cursor cursor, int offset) {
        AppointmentInfo entity = new AppointmentInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // appointmentUID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // appointmentTitle
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // publishTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // whoPublish
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // whoPublishStuID
            cursor.getInt(offset + 5), // whoPublishStuGrade
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // whoPublishStuMajor
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // location
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // appointmentTime
            cursor.getDouble(offset + 9), // appointmentLatitude
            cursor.getDouble(offset + 10), // appointmentLongtitude
            cursor.getInt(offset + 11), // appointmentNum
            cursor.getInt(offset + 12), // appointmentType
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // appointmentTypeText
            cursor.getInt(offset + 14), // appointmentStatus
            cursor.isNull(offset + 15) ? null : appointmentStuInfoListConverter.convertToEntityProperty(cursor.getString(offset + 15)) // appointmentStuInfoList
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AppointmentInfo entity, int offset) {
        entity.setAppointmentUID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setAppointmentTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPublishTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWhoPublish(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setWhoPublishStuID(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setWhoPublishStuGrade(cursor.getInt(offset + 5));
        entity.setWhoPublishStuMajor(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLocation(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setAppointmentTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAppointmentLatitude(cursor.getDouble(offset + 9));
        entity.setAppointmentLongtitude(cursor.getDouble(offset + 10));
        entity.setAppointmentNum(cursor.getInt(offset + 11));
        entity.setAppointmentType(cursor.getInt(offset + 12));
        entity.setAppointmentTypeText(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setAppointmentStatus(cursor.getInt(offset + 14));
        entity.setAppointmentStuInfoList(cursor.isNull(offset + 15) ? null : appointmentStuInfoListConverter.convertToEntityProperty(cursor.getString(offset + 15)));
     }
    
    @Override
    protected final String updateKeyAfterInsert(AppointmentInfo entity, long rowId) {
        return entity.getAppointmentUID();
    }
    
    @Override
    public String getKey(AppointmentInfo entity) {
        if(entity != null) {
            return entity.getAppointmentUID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AppointmentInfo entity) {
        return entity.getAppointmentUID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

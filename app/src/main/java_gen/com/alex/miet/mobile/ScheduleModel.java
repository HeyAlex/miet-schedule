package com.alex.miet.mobile;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "SCHEDULE_MODEL".
 */
@Entity(active = true)
public class ScheduleModel {

    @Id
    private String group;
    private String semestr;

    /**
     * Used to resolve relations
     */
    @Generated
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated
    private transient ScheduleModelDao myDao;

    @ToMany(joinProperties = {
            @JoinProperty(name = "group", referencedName = "groupName")
    })
    private List<LessonModel> lessons;

    @Generated
    public ScheduleModel() {
    }

    public ScheduleModel(String group) {
        this.group = group;
    }

    @Generated
    public ScheduleModel(String group, String semestr) {
        this.group = group;
        this.semestr = semestr;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getScheduleModelDao() : null;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSemestr() {
        return semestr;
    }

    public void setSemestr(String semestr) {
        this.semestr = semestr;
    }

    /**
     * To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated
    public List<LessonModel> getLessons() {
        if (lessons == null) {
            __throwIfDetached();
            LessonModelDao targetDao = daoSession.getLessonModelDao();
            List<LessonModel> lessonsNew = targetDao._queryScheduleModel_Lessons(group);
            synchronized (this) {
                if (lessons == null) {
                    lessons = lessonsNew;
                }
            }
        }
        return lessons;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated
    public synchronized void resetLessons() {
        lessons = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}
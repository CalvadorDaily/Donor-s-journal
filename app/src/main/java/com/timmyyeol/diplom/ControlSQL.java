package com.timmyyeol.diplom;

import  android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class ControlSQL extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "android.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "table1";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LOGIN = "LOGIN";
    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_LOGIN
            + " text not null" + ");";

    public ControlSQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        ContentValues initialValues = createContentValues("");
        db.insert(DATABASE_TABLE, null, initialValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS table1");
        onCreate(db);
    }


    public long createNewTable(String LOGIN) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = createContentValues(LOGIN);
        long row = db.insert(DATABASE_TABLE, null, initialValues);
        db.close();
        return row;
    }

    public boolean updateTable(long rowId, String LOGIN) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateValues = createContentValues(LOGIN);
        return db.update(DATABASE_TABLE, updateValues, COLUMN_ID + "=" + rowId,
                null) > 0;
    }

    public void deleteTable(long rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, COLUMN_ID + "=" + rowId, null);
        db.close();
    }

    public Cursor getFullTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(DATABASE_TABLE, new String[] { COLUMN_ID,
                        COLUMN_LOGIN }, null,
                null, null, null, null);
    }

    public Cursor getTable(long rowId) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, DATABASE_TABLE,
                new String[] { COLUMN_ID, COLUMN_LOGIN }, COLUMN_ID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    private ContentValues createContentValues(String LOGIN) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN, LOGIN);
        return values;
    }
}
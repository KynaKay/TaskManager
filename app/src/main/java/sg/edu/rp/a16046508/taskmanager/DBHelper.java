package sg.edu.rp.a16046508.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VER = 1;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASK_NAME = "taskName";
    private static final String COLUMN_DESCRIPTION = "description";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASK_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT ) ";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

        for(int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, "Data number " + i);
            db.insert(TABLE_TASK, null, values);
        }
        Log.i("info", "task records inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_TASK + " ADD COLUMN taskName TEXT ");
    }

    public long insertTask(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME, taskName);
        long result = db.insert(TABLE_TASK, null, values);
        Log.d("SQL Insert ",""+ result);
        return result;
    }

    public ArrayList<String> getAllTask() {
        ArrayList<String> task = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_DESCRIPTION + ", "
                + COLUMN_TASK_NAME
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String content = cursor.getString(1);
                task.add("ID:" + id + ", " + content);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return task;
    }

    public ArrayList<Task> getAllTask(String keyword) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TASK_NAME, COLUMN_DESCRIPTION};
        String condition = COLUMN_TASK_NAME + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_TASK, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String taskName = cursor.getString(1);
                String description = cursor.getString(1);
                Task task = new Task(id, taskName, description);
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

    public int updateNote(Task data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_TASK_NAME, data.getTaskName());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getID())};
        int result = db.update(TABLE_TASK, values, condition, args);
        db.close();
        return result;
    }

    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_TASK, condition, args);
        db.close();
        return result;
    }
}

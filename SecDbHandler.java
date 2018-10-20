import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SecDbHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "secDB.db";
    public static final String TABLE_STUDENTS = "students";
    public static final String COLUMN_ID = "StudentID";
    public static final String COLUMN_NAME = "StudentName";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_CLOCKIN = "Clockin";
    public static final String COLUMN_CLOCKOUT = "Clockout";

    public SecDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        //super calls SQLiteOpenHelper
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_STUDENTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT " + COLUMN_PASSWORD + " TEXT " + COLUMN_CLOCKIN + " TEXT " + COLUMN_CLOCKOUT
                + " TEXT " + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public void addHandler(Student student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getID());
        values.put(COLUMN_NAME, student.getStudentName());
        values.put(COLUMN_PASSWORD, student.getPassword());
        //values.put(COLUMN_CLOCKIN, student.getClockin());
        //values.put(COLUMN_CLOCKOUT, student.getClockout());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }//end of addHandler

    public Student findHandler(String password) {
        String query = "Select * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_PASSWORD + " = '" + password + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setID(Integer.parseInt(cursor.getString(0)));
            student.setStudentName(cursor.getString(2));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        //return password and column id
        return student;
    }
    public String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }//end of load
    public boolean updateTimeHandler(int ID, String name, String password, String clockin, String clockout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, ID);
        //cv.put(COLUMN_NAME, name);
        //cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_CLOCKIN, clockin);
        if(clockout != ""){
            clockout = "";
            cv.put(COLUMN_CLOCKOUT, clockout);
            return db.update(TABLE_STUDENTS, cv, COLUMN_CLOCKIN + "=" + clockin, null) > 0;
        }
        cv.put(COLUMN_CLOCKOUT, clockout);
        return db.update(TABLE_STUDENTS, cv, COLUMN_CLOCKOUT + "=" + clockout, null) > 0;
    }
}

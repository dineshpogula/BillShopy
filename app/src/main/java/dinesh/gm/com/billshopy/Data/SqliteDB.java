package dinesh.gm.com.billshopy.Data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dpogula on 10/18/2017.
 */

public class SqliteDB extends SQLiteOpenHelper{

    private SQLiteDatabase database;

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SqliteDB.db";

    //Table Names
    public static final String TABLE_NAME = "PEOPLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";


    public SqliteDB (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRST_NAME + " VARCHAR, " + COLUMN_LAST_NAME + " VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(BillingModel billing) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, billing.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, billing.getLastName());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void insertRecordAlternate(BillingModel billing) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_FIRST_NAME + "," + COLUMN_LAST_NAME + ") VALUES('" + billing.getFirstName() + "','" + billing.getLastName() + "')");
        database.close();
    }

    public ArrayList<BillingModel> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<BillingModel> bills = new ArrayList<BillingModel>();
        BillingModel billingModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                billingModel = new BillingModel();
                billingModel.setID(cursor.getString(0));
                billingModel.setFirstName(cursor.getString(1));
                billingModel.setLastName(cursor.getString(2));

                bills.add(billingModel);
            }
        }
        cursor.close();
        database.close();

        return bills;
    }

    public ArrayList<BillingModel> getAllRecordsAlternate() {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<BillingModel> bills = new ArrayList<BillingModel>();
        BillingModel billingModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                billingModel = new ContactModel();
                billingModel.setID(cursor.getString(0));
                billingModel.setFirstName(cursor.getString(1));
                billingModel.setLastName(cursor.getString(2));

                contacts.add(billingModel);
            }
        }
        cursor.close();
        database.close();

        return bills;
    }

    public void updateRecord(BillingModel billing) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, billing.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, billing.getLastName());
        database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public void updateRecordAlternate(BillingModel billing) {
        database = this.getReadableDatabase();
        database.execSQL("update " + TABLE_NAME + " set " + COLUMN_FIRST_NAME + " = '" + contact.getFirstName() + "', " + COLUMN_LAST_NAME + " = '" + billing.getLastName() + "' where " + COLUMN_ID + " = '" + billing.getID() + "'");
        database.close();
    }

    public void deleteAllRecords() {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, null, null);
        database.close();
    }

    public void deleteAllRecordsAlternate() {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME);
        database.close();
    }

    public void deleteRecord(BillingModel billing) {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{billing.getID()});
        database.close();
    }

    public void deleteRecordAlternate(BillingModel billing) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME + " where " + COLUMN_ID + " = '" + billing.getID() + "'");
        database.close();
    }

    public ArrayList<String> getAllTableName()
    {
        database = this.getReadableDatabase();
        ArrayList<String> allTableNames=new ArrayList<String>();
        Cursor cursor=database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'",null);
        if(cursor.getCount()>0)
        {
            for(int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToNext();
                allTableNames.add(cursor.getString(cursor.getColumnIndex("name")));
            }
        }
        cursor.close();
        database.close();
        return allTableNames;
    }

}

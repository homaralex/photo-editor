package projekt_android.photoeditor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotrek on 2014-12-10.
 */
public abstract class ImageDataSource {

    protected ImageSQLHelper helper;
    protected SQLiteDatabase database;
    protected String[] allColumns;

    public ImageDataSource(Context context) {
        this.helper = new ImageSQLHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public abstract String getImageColumnName();

    public abstract String getTableName();

    public void addImage(String url) {
        ContentValues values = new ContentValues();
        values.put(this.getImageColumnName(), url);
        database.insert(this.getTableName(), null, values);
    }

    public List<String> getAllUrls() {
        List<String> urls = new ArrayList<String>();

        Cursor cursor = database.query(this.getTableName(), allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String url = cursor.getString(1);
            urls.add(url);
        }

        return urls;
    }
}

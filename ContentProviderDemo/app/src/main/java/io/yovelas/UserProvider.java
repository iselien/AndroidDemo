package io.yovelas;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class UserProvider extends ContentProvider {


//    // Defines a handle to the Room database
//    private AppDatabase appDatabase;
//
//    // Defines a Data Access Object to perform the database operations
//    private UserDao userDao;
//
//    // Defines the database name
//    private static final String DBNAME = "mydb";
//
//    public boolean onCreate() {


//
//        // Creates a new database object.
//        appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, DBNAME).build();
//
//        // Gets a Data Access Object to perform the database operations
//        userDao = appDatabase.getUserDao();
//
//        return true;
//    }
//
//    ...
//
//    // Implements the provider's insert method
//    public Cursor insert(Uri uri, ContentValues values) {
//        // Insert code here to determine which DAO to use when inserting data, handle error conditions, etc.
//    }
//
//






    static final String PROVIDER_NAME = "com.tutlane.contentprovider.UserProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/users";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String id = "id";
    static final String name = "name";
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    private static HashMap<String, String> values;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", uriCode);
        uriMatcher.addURI(PROVIDER_NAME, "users/*", uriCode);
    }



    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/users";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

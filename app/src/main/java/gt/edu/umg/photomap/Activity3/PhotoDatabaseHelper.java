package gt.edu.umg.photomap.Activity3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class PhotoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PhotoMap.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_FOTOS = "Fotos";

    public PhotoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FOTOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT," +
                "descripcion TEXT," +
                "ubicacion TEXT," +
                "fecha TEXT," +
                "miniatura BLOB" +
                ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Fotos");
        onCreate(db);
    }

    // Método para guardar una foto en la base de datos
    public void guardarFoto(String titulo, String descripcion, String fecha, String ubicacion, String rutaImagen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("descripcion", descripcion);
        values.put("fecha", fecha);
        values.put("ubicacion", ubicacion);
        values.put("rutaImagen", rutaImagen);

        db.insert("Fotos", null, values);
        db.close();
    }

    private byte[] convertirBitmapABytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    // Método para recuperar la miniatura desde un blob
    public Bitmap convertirBytesABitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}

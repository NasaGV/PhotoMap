package gt.edu.umg.photomap.Activity1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import gt.edu.umg.photomap.Activity3.PhotoDatabaseHelper;
import gt.edu.umg.photomap.R;

public class AdaptadorFotos extends RecyclerView.Adapter<AdaptadorFotos.FotoViewHolder> {

    private void cargarFotosDesdeBD() {
        PhotoDatabaseHelper dbHelper = new PhotoDatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("Fotos", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
            String ubicacion = cursor.getString(cursor.getColumnIndexOrThrow("ubicacion"));
            String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));

            // Cargar miniatura desde BLOB
            byte[] miniaturaBytes = cursor.getBlob(cursor.getColumnIndexOrThrow("miniatura"));
            Bitmap miniatura = dbHelper.convertirBytesABitmap(miniaturaBytes);

            listaFotos.add(new Foto(id, titulo, descripcion, ubicacion, fecha, miniatura));
        }
        cursor.close();
        db.close();
    }


    public AdaptadorFotos(Context context) {
        this.context = context;
        this.listaFotos = new ArrayList<>();
        cargarFotosDesdeBD(); // Cargar fotos desde la BD al iniciar
    }

    // Método de ejemplo para obtener una miniatura
    private Bitmap obtenerMiniaturaDesdeBD(int id) {
        // Implementación específica para obtener la miniatura desde la base de datos
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder); // Placeholder
    }

    private List<Foto> listaFotos;
    private Context context;

    public AdaptadorFotos(List<Foto> listaFotos) {
        this.context = context;
        this.listaFotos = listaFotos;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_foto, parent, false);
        return new FotoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {
        Foto foto = listaFotos.get(position);
        holder.descripcionFoto.setText(foto.getDescripcion());
        holder.fechaFoto.setText(foto.getFecha());
        holder.fotoMiniatura.setImageBitmap(foto.getMiniatura());
    }

    @Override
    public int getItemCount() {
        return listaFotos.size();
    }

    static class FotoViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoMiniatura;
        TextView descripcionFoto;
        TextView fechaFoto;

        FotoViewHolder(View itemView) {
            super(itemView);
            fotoMiniatura = itemView.findViewById(R.id.fotoMiniatura);
            descripcionFoto = itemView.findViewById(R.id.descripcionFoto);
            fechaFoto = itemView.findViewById(R.id.fechaFoto);
        }
    }
}

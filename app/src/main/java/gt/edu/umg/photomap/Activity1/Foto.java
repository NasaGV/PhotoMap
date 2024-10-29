package gt.edu.umg.photomap.Activity1;

import android.graphics.Bitmap;

public class Foto {
    private int id;
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private String fecha;
    private Bitmap miniatura;

    public Foto(int id, String titulo, String descripcion, String ubicacion, String fecha, Bitmap miniatura) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.miniatura = miniatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Bitmap getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(Bitmap miniatura) {
        this.miniatura = miniatura;
    }
}
package gt.edu.umg.photomap.Activity1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import gt.edu.umg.photomap.R;

public class Activity1 extends AppCompatActivity {
    private AdaptadorFotos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewGallery);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adaptador = new AdaptadorFotos(this);  // Inicializa el adaptador con contexto
        recyclerView.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adaptador.notifyDataSetChanged(); // Refrescar los datos al volver a la actividad
    }
}


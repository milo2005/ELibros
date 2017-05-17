package unicauca.movil.libros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import unicauca.movil.libros.models.Libro;
import unicauca.movil.libros.util.Data;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre, autor, paginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nombre = (EditText) findViewById(R.id.nombre);
        autor = (EditText) findViewById(R.id.autor);
        paginas = (EditText) findViewById(R.id.paginas);

        Button save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nom =  nombre.getText().toString();
        String aut = autor.getText().toString();
        String pag =  paginas.getText().toString();

        Libro libro =  new Libro();
        libro.setNombre(nom);
        libro.setAutor(aut);
        libro.setPaginas(Integer.parseInt(pag));

        Data.libros.add(libro);

        finish();
    }
}

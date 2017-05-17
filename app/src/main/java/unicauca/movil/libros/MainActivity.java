package unicauca.movil.libros;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import unicauca.movil.libros.adapters.LibroAdapter;
import unicauca.movil.libros.models.Libro;
import unicauca.movil.libros.util.Data;

public class MainActivity extends AppCompatActivity {

    List<Libro> data;
    LibroAdapter adapter;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);

        data = Data.libros;
        adapter = new LibroAdapter(getLayoutInflater(), data);
        list.setAdapter(adapter);

        registerForContextMenu(list);

        loadLibros();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    public void loadLibros(){
        Libro libro1 = new Libro();
        libro1.setNombre("El mejor Libro");
        libro1.setAutor("Dario Chamorro");
        libro1.setPaginas(1000);

        Libro libro2 = new Libro();
        libro2.setNombre("Android Re PRO");
        libro2.setAutor("Dario Chamorro");
        libro2.setPaginas(1000);

        Libro libro3 = new Libro();
        libro3.setNombre("El mejor Libro 2");
        libro3.setAutor("Dario Chamorro");
        libro3.setPaginas(1000);

        data.add(libro1);
        data.add(libro2);
        data.add(libro3);

        adapter.notifyDataSetChanged();
    }

    //region Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:

                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);

                break;
            case R.id.action_info:
                Toast.makeText(this, "Informacion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this, "Acerca De", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        pos = info.position;

        switch (item.getItemId()){
            case R.id.action_delete:
                deleteLibro();
                break;
            case R.id.action_edit:
                Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void deleteLibro() {
        AlertDialog alert =  new AlertDialog.Builder(this)
                .setTitle("Eliminar Titulo")
                .setMessage("¿ Desea eliminar "+data.get(pos).getNombre()+" ?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                })
                .create();

        alert.show();
    }
    //endregion
}

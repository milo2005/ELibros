package unicauca.movil.libros.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import unicauca.movil.libros.R;
import unicauca.movil.libros.models.Libro;

public class LibroAdapter extends BaseAdapter{

    LayoutInflater inflater;
    List<Libro> data;

    public LibroAdapter(LayoutInflater inflater, List<Libro> data) {
        this.inflater = inflater;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v =  convertView;
        if(v == null)
            v =  inflater.inflate(R.layout.template_libro, parent, false);

        Libro libro = data.get(position);

        TextView nombre = (TextView) v.findViewById(R.id.nombre);
        TextView autor = (TextView) v.findViewById(R.id.autor);
        TextView pag = (TextView) v.findViewById(R.id.pag);

        nombre.setText(libro.getNombre());
        autor.setText(libro.getAutor());
        pag.setText(""+libro.getPaginas());


        return v;
    }
}

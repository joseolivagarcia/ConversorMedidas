package joseoliva.com.conversordemedidas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends BaseAdapter { //extiende de BaseAdapter y hay que agregar sus metodos

    private static LayoutInflater inflater = null; //instancia un elemento xml(layout) en la correspondiente vista

    //creo als var necesarias
    Context contexto;
    String[][] datos;
    int[] datosImg;

    //creo el constructor que recibe los parametros necesarios para rellenar los items
    public Adaptador(Context contexto, String[][] datos, int[] imagenes){
        this.contexto = contexto;
        this.datos = datos;
        this.datosImg = imagenes;

        //inicializo el inflater
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    //creo automaticamente los metodos necesarios al extender de BaseAdapter
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        //creo las var necesarias y las relleno con sus correspondientes datos
        final View vista = inflater.inflate(R.layout.elemento_lista,null);//aqui indico que el elemento_lista es el que se representara
        TextView titulo = (TextView)vista.findViewById(R.id.titulo);
        TextView subtitulo = (TextView)vista.findViewById(R.id.subtitulo);
        ImageView imagen = (ImageView)vista.findViewById(R.id.imgicono);

        titulo.setText(datos[i][0]);
        subtitulo.setText(datos[i][1]);
        imagen.setImageResource(datosImg[i]);

        return vista;
    }
    @Override
    public int getCount() {return datosImg.length; } //la lista tendra tantos elementos como tenga el array

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}

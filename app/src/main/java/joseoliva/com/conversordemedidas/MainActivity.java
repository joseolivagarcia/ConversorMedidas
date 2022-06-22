package joseoliva.com.conversordemedidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lista; //declaro una var tipo listview para hacer referencia a la lista(view lista que se identifica como lvLista)

    //creo un array para rellenar la lista.Es de dos dimensiones porque tengo 2 textos en cada item
    String[][] datos = {
            {"PESO", "mg, g, kg, ton"},
            {"VOLUMEN", "ml, cl, dl, l"},
            {"LONGITUD", "cm, dm , m, dam"},
            {"TEMPERATURA", "Celsius, Kelvin"},
            {"CAPACIDAD HDD", "B, Mb, Gb, Tb"}
    };

    //creo otro array para las imagenes que hay en cada item
    int[] datosimg = {R.drawable.peso, R.drawable.volumen, R.drawable.longitud, R.drawable.temperatura, R.drawable.hdd};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lvLista); //inicio la var lista diciendole cual es el elemento(view) que representa
        lista.setAdapter(new Adaptador(this,datos,datosimg));//le paso el adaptador que se rellena al pasar paramentros a la clase Adaptador
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String medida = datos[position][0].toString();
                int posicion = position;

                Intent intent = new Intent(MainActivity.this,Activity_Conversor.class);
                intent.putExtra("medida", medida);
                intent.putExtra("imagen",posicion);
                startActivity(intent);
            }
        });
    }

}
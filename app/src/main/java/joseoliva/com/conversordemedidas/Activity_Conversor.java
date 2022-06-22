package joseoliva.com.conversordemedidas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Conversor extends AppCompatActivity {

    public int vista;
    public String medida;
    public int imagenRecibida;
    public int[] datosimg = {R.drawable.peso, R.drawable.volumen, R.drawable.longitud, R.drawable.temperatura, R.drawable.hdd};
    public EditText txtvalor;
    public TextView resultado;
    public EditText resumen;
    public Button botonCalcular;
    public Spinner listaOrigen;
    public Spinner listaDestino;
    public Double resultadofinal; //usare esta var para calcular las conversiones y poder usarla en varios metodos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        botonCalcular = findViewById(R.id.btnConvertir);
        Bundle datos = getIntent().getExtras(); //en el bundle se almacenan los datos recibidos
        medida = datos.getString("medida"); //recojo el dato que viene del intent del MainActivity
        imagenRecibida = datos.getInt("imagen");
        
        TextView txtfijo = findViewById(R.id.txtfijo);
        ImageView icono = (ImageView)findViewById(R.id.icono);

        txtfijo.setText("INTRODUCE LA CANTIDAD A CONVERTIR");
        icono.setImageResource(datosimg[imagenRecibida]);
        txtvalor = findViewById(R.id.txtvalor);
        resultado = findViewById(R.id.txtResultado);
        resumen = findViewById(R.id.txtresumen);

        vista = 0;
        switch(medida){
            case "PESO":
                vista = R.array.arrayPeso;
                break;
            case "VOLUMEN":
                vista = R.array.arrayVolumen;
                break;
            case "LONGITUD":
                vista = R.array.arrayLongitud;
                break;
            case "TEMPERATURA":
                vista = R.array.arrayTemperatura;
                break;
            case "CAPACIDAD HDD":
                vista = R.array.arrayHdd;
                break;
            default:
                vista = R.array.arrayUnidades;
        }

        listaOrigen = (Spinner)findViewById(R.id.spinnerOrigen);
        listaDestino = (Spinner)findViewById(R.id.spinnerDestino);
        ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(this,vista, android.R.layout.simple_spinner_item);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaOrigen.setAdapter(adap);
        listaDestino.setAdapter(adap);

        //doy funcionalidad al boton
        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cuando presione el boton segun la unidad de medida selecionada iremos a una funcion u otra
                switch(medida){
                    case "PESO":
                        conviertePeso();
                        break;
                    case "VOLUMEN":
                        convierteVolumen();
                        break;
                    case "LONGITUD":
                        convierteLongitud();
                        break;
                    case "TEMPERATURA":
                        convierteTemperatura();

                        break;
                    case "CAPACIDAD HDD":
                        convierteBytes();
                        break;
                    default:
                        vista = R.array.arrayUnidades;
                }
            }
        });
    }

    public void conviertePeso() {
        //obtengo la unidad seleccionada en cada spinner
        int unidadOrigen = listaOrigen.getSelectedItemPosition();
        int unidadDestino = listaDestino.getSelectedItemPosition();
        //paso a Double el valor que el usuario a escrito para convertir
        Double valor = Double.parseDouble(txtvalor.getText().toString());
        //resto la pos origen y destino para saber cuanta diferencia hay entre una y otra
        int resta = unidadOrigen - unidadDestino;
        if(resta>0){
            resultadofinal = valor * Math.pow(1000,resta);
            resultado.setText(resultadofinal.toString());
        }
        if(resta<0){
            resultadofinal = valor / Math.pow(1000,-resta);
            resultado.setText(resultadofinal.toString());
        }
        if(resta == 0){
            resultadofinal = Double.parseDouble(txtvalor.getText().toString());//necesito esta linea para que no pete la del resumen
            resultado.setText(txtvalor.getText().toString());
        }
        resumen.setText(txtvalor.getText().toString() + " " + listaOrigen.getSelectedItem().toString() +
                " son " + resultadofinal.toString() + " " + listaDestino.getSelectedItem().toString());
    }

    public void convierteVolumen() {
        //obtengo la unidad seleccionada en cada spinner
        int unidadOrigen = listaOrigen.getSelectedItemPosition();
        int unidadDestino = listaDestino.getSelectedItemPosition();
        //paso a Double el valor que el usuario a escrito para convertir
        Double valor = Double.parseDouble(txtvalor.getText().toString());
        //resto la pos origen y destino para saber cuanta diferencia hay entre una y otra
        int resta = unidadOrigen - unidadDestino;
        if(resta>0){
            resultadofinal = valor * Math.pow(10,resta);
            resultado.setText(resultadofinal.toString());
        }
        if(resta<0){
            resultadofinal = valor / Math.pow(10,-resta);
            resultado.setText(resultadofinal.toString());
        }
        if(resta == 0){
            resultadofinal = Double.parseDouble(txtvalor.getText().toString());//necesito esta linea para que no pete la del resumen
            resultado.setText(txtvalor.getText().toString());
        }
        resumen.setText(txtvalor.getText().toString() + " " + listaOrigen.getSelectedItem().toString() +
                " son " + resultadofinal.toString() + " " + listaDestino.getSelectedItem().toString());
    }

    public void convierteLongitud(){
        //obtengo la unidad seleccionada en cada spinner
        int unidadOrigen = listaOrigen.getSelectedItemPosition();
        int unidadDestino = listaDestino.getSelectedItemPosition();
        //paso a Double el valor que el usuario a escrito para convertir
        Double valor = Double.parseDouble(txtvalor.getText().toString());
        //resto la pos origen y destino para saber cuanta diferencia hay entre una y otra
        int resta = unidadOrigen - unidadDestino;
        if(resta>0){
            resultadofinal = valor * Math.pow(10,resta);
            resultado.setText(resultadofinal.toString());
        }
        if(resta<0){
            resultadofinal = valor / Math.pow(10,-resta);
            resultado.setText(resultadofinal.toString());
        }
        if(resta == 0){
            resultadofinal = Double.parseDouble(txtvalor.getText().toString());//necesito esta linea para que no pete la del resumen
            resultado.setText(txtvalor.getText().toString());
        }
        resumen.setText(txtvalor.getText().toString() + " " + listaOrigen.getSelectedItem().toString() +
                " son " + resultadofinal.toString() + " " + listaDestino.getSelectedItem().toString());
    }

    public void convierteTemperatura(){
        int medidaOrigen = listaOrigen.getSelectedItemPosition();
        int medidaDestino = listaDestino.getSelectedItemPosition();
        Double valor = Double.parseDouble(txtvalor.getText().toString());
        if(medidaOrigen == 0 && medidaDestino == 1){
            resultadofinal = valor + 273.15;
            resultado.setText(resultadofinal.toString());
        }else if(medidaOrigen == 1 && medidaDestino == 0){
            resultadofinal = valor - 273.15;
            resultado.setText(resultadofinal.toString());
        }else{
            resultadofinal = Double.parseDouble(txtvalor.getText().toString());//necesito esta linea para que no pete la del resumen
            resultado.setText(txtvalor.getText().toString());
        }
        resumen.setText(txtvalor.getText().toString() + " " + listaOrigen.getSelectedItem().toString() +
                " son " + resultadofinal.toString() + " " + listaDestino.getSelectedItem().toString());

    }

    public void convierteBytes(){
        //obtengo la unidad seleccionada en cada spinner
        int unidadOrigen = listaOrigen.getSelectedItemPosition();
        int unidadDestino = listaDestino.getSelectedItemPosition();
        //paso a Double el valor que el usuario a escrito para convertir
        Double valor = Double.parseDouble(txtvalor.getText().toString());
        //resto la pos origen y destino para saber cuanta diferencia hay entre una y otra
        int resta = unidadOrigen - unidadDestino;
        //si la resta da positivo tendre que multiplicar por 1024 tantas veces como sea el resultado
        //si la resta es negativa tendre que dividir por 1024 tantas veces como de el resultado
        if(resta>0){
            resultadofinal = valor * Math.pow(1024,resta);
            resultado.setText(resultadofinal.toString());
        }
        if(resta<0){
            resultadofinal = valor / Math.pow(1024,-resta);
            resultado.setText(resultadofinal.toString());
        }
        if(resta == 0){
            resultadofinal = Double.parseDouble(txtvalor.getText().toString());//necesito esta linea para que no pete la del resumen
            resultado.setText(txtvalor.getText().toString());
        }
        resumen.setText(txtvalor.getText().toString() + " " + listaOrigen.getSelectedItem().toString() +
                " son " + resultadofinal.toString() + " " + listaDestino.getSelectedItem().toString());
    }

    //creo los dos metodos para el menu de menu_en_activity
    @Override
    public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_opciones,mimenu);//esto recupera el menu
        return true;
    }
    //este segundo metodo es para dar funcionalidad a las opciones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id=opcion_menu.getItemId();//aqui recupero el id de opcion_menu

        if(id==R.id.menu_buscar){//si hemos pulsado sobre la opcion configuracion
            buscar();
            return true;
        }

        if(id==R.id.volver){//si hemos pulsado sobre info
            volver(null); //llamo al metodo que me lleva a la actividad de inicio
            return true;
        }
        return super.onOptionsItemSelected(opcion_menu); //si no pulsamos ninguna opcion devolvemos el propio item
    }

    public void volver(View view){

        //ahora trabajamos la Intencion (algo parecido a los eventos)
        //aqui la intencion es que al pulsar el boton me lleve a la otra actividad
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void buscar(){
        Toast.makeText(this,"Botón deshabilitado...", Toast.LENGTH_LONG).show();
    }
}



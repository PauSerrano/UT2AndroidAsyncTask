package es.schooleando.ejercicio2;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public Button botonDescarga;
    private TextView tvvalores;
    private TextView tvPorcentaje;
    private DescargaAsyncTask tarea=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonDescarga = (Button)findViewById(R.id.botonDescarga);
        tvvalores = (TextView) findViewById(R.id.tvProgreso1);
        tvPorcentaje = (TextView) findViewById(R.id.tvPorcentaje);

        botonDescarga.setText("Descargar");



    }

    //Al hacer click al boton
    public void onClickBotonDescarga(View view) {


        //si el texto es de descarga
        if (botonDescarga.getText().toString().equalsIgnoreCase("Descargar")){
            //empecamos la tarea asincrona del asyntask
            tarea = new DescargaAsyncTask();
            tarea.execute();
            //si en el boton pone cancelar y se está ejecutando la tarea
        } else if ((botonDescarga.getText().toString().equalsIgnoreCase("Cancelar")) & (tarea!=null)){
            //Cancelamos la tarea
            tarea.cancel(true);
        }
    }



    //***************** CREAMOS LA ASYNCTASK *****************
    public class DescargaAsyncTask extends AsyncTask<String, Integer, String> {

        //declaramos una barra de progreso
        private ProgressBar progreso;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //CREAMOS LA barra DE PROGRESO
            progreso = (ProgressBar) findViewById(R.id.progressBar);

            //Cambiamos el texto del boton
            botonDescarga.setText("Cancelar");
        }

        @Override
        protected String doInBackground(String... params) {

            int valorProgreso=0;

            //Realizamos la acción en segundo plano
            for (int i = 0; i<100;i++ ){
                valorProgreso++;
                publishProgress(valorProgreso);
                SystemClock.sleep(50);
                //Vamos comprobando si es cancelada
                if (isCancelled()){
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            //Cuando acaba la descarga volvemos a cambiar el texto del botón.
            botonDescarga.setText("Descargar");
            //Indicamos que se ha realizado la descarga
            Toast.makeText(getApplicationContext(),"Se ha realizado la descarga", Toast.LENGTH_LONG).show();
            //Reiniciamos los valores de los elementos que nos indicaban el proceso
            progreso.setProgress(0);
            tvvalores.setText("0");
            tvPorcentaje.setText("0 de 100");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            //Indicamos la evolucion del progreso en estas text View
            tvvalores.setText((values[0]).toString());
            tvPorcentaje.setText((values[0]).toString()+" de 100");
            //Vamos indicando el progreso a la progressBar
            progreso.setProgress(values[0]);
        }

        @Override
        protected void onCancelled() {

            //Si la actividad es cancelada, se mostrá un Toast se bloqueará el botón
            Toast.makeText(getApplicationContext(),"Se ha cancelado la actividad", Toast.LENGTH_LONG).show();
            botonDescarga.setText("Cancelado");
            botonDescarga.setEnabled(false);
        }
    }
    //*******************************************************************




}

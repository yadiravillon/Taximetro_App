package ec.edu.upse.taximetro_app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

public class ConsultasActivity extends Activity {
	EditText carrera, total;
    ListView lista;
    DatePicker desde, hasta;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultas);
		Inicializar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultas, menu);
		return true;
	}
	
	public void Inicializar(){
		desde = (DatePicker) findViewById(R.id.datePickerDesde);
		hasta = (DatePicker) findViewById(R.id.datePickerHast);
		lista = (ListView) findViewById(R.id.listViewConsulta);
		carrera = (EditText) findViewById(R.id.editTextCcarreras);
		total = (EditText) findViewById(R.id.editTextVtotal);
		
 	}

public void onBuscar(View boton){
		
	}
	
	public void onRegresar(View boton){
		Intent intent =new Intent(this,FuncionesActivity.class);
		startActivity(intent);
	}
	
}

package ec.edu.upse.taximetro_app;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ToggleButton;

public class TaxiActivity extends Activity implements LocationListener{
	EditText et_Km, et_$,et_Partida, et_Llegada;
	 Button Guardar, Cancelar;
	 ToggleButton toggle;
	 Chronometer cronometro;
	// GoogleMap mapa;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taxi);
		
		Inicializar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.taxi, menu);
		return true;
	}
	
	public void Inicializar(){
		et_Km = (EditText) findViewById(R.id.editTextkm);
		et_$ = (EditText) findViewById(R.id.EditTextValor);
		et_Partida = (EditText) findViewById(R.id.EditTextPartida);
		et_Llegada = (EditText) findViewById(R.id.EditTextLlegada);
		toggle = (ToggleButton) findViewById(R.id.toggleButton);
		cronometro = (Chronometer) findViewById(R.id.cronometro);
	//	mapa = findViewById(R.id.fragmentMapa);
 	}
	

	public void onCancelar(View boton){
		Intent intent =new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	
	public void onGuardar(View boton){
		
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}

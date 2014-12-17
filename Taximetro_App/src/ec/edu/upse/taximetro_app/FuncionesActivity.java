package ec.edu.upse.taximetro_app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FuncionesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_funciones);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.funciones, menu);
		return true;
	}
	
	
	public void onInicio(View boton){
		Intent intent =new Intent(this,MapaActivity.class);
		startActivity(intent);
	}
	
	public void onConsultas(View boton){
		Intent intent =new Intent(this,ConsultasActivity.class);
		startActivity(intent);
	}
	
	

}

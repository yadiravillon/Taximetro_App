package ec.edu.upse.taximetro_app;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText et_usuario, et_contrasenia;
	private Button btn_Acceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inicializar();
        btn_Acceder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WSEnvio_datos registro = new WSEnvio_datos();
				registro.execute();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void Inicializar(){
    	et_usuario = (EditText) findViewById(R.id.editTextUsuario);
    	et_contrasenia = (EditText) findViewById(R.id.editTextContrasenia);
    	
    }
    
    private class WSEnvio_datos extends AsyncTask<String, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			boolean resul = true;
			final String NAMESPACE = "";
			final String URL = "";
			final String METHOD_NAME = "";
			final String SOAP_ACTION = "";
			
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("usuario", et_usuario.getText().toString());
			request.addProperty("contrasenia", et_contrasenia.getText().toString());
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE transporte = new HttpTransportSE(URL);
			
			try {
				transporte.call(SOAP_ACTION, envelope);
				SoapPrimitive resultado_xml = (SoapPrimitive)envelope.getResponse();
				String res = resultado_xml.toString();
				if(!res.equals("1"))
					resul = false;
			
			} catch (Exception e) {
				 resul = false;
			}
			return resul;
		}}
    
    
    public void Registrarse_Evento(View v){
    	Intent intent = new Intent (this, RegistroActivity.class);
    	startActivity(intent);
    }
   
}

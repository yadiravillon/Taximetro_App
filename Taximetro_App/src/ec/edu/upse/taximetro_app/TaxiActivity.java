package ec.edu.upse.taximetro_app;

import java.util.ArrayList;
import java.util.Date;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import ec.edu.upse.taximetro_app.modelo.Carrera;
import ec.edu.upse.taximetro_app.modelo.DBTaximetro;
import ec.edu.upse.taximetro_app.modelo.Tarifa;




import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TaxiActivity extends Activity implements LocationListener{
	
	TextView et_Km, et_$,et_Partida, et_Llegada;
	Button Guardar, Cancelar;
	ToggleButton button_O_O;
    Chronometer CronometroTiempo;
	 
	// daclarar variable que representa al mapa
		GoogleMap mapa;
		Location locationI, locationF;
		LocationManager locationManager;
		String proveedor;
		PolylineOptions polilinea_options;
		LatLng latlng;
		Polyline polilinea;
		
		//variables de ubicacion
		double latitud_inicio, longitud_inicio, latitud_final, longitud_final, distancia_total=0;
		int segundos_consumidos=0, total_segundos_p=0;
	 ArrayList<Tarifa> tarifa = new ArrayList<Tarifa>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taxi);
		
		Inicializar();
		if(mapa==null){
			Toast.makeText(this, "no se pudo crear mapa", Toast.LENGTH_LONG).show();
		}else{
			mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			mapa.getUiSettings().setZoomControlsEnabled(true);
			mapa.getUiSettings().setCompassEnabled(true);
		}
		
	}
	
	
	public void agregarMarca(double latitud, double longitud, String titulo, String mensaje){
		MarkerOptions marca = new MarkerOptions();
		LatLng ubicacion = new LatLng(latitud, longitud);
		marca.position(ubicacion);
		marca.title(titulo);
		marca.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
		marca.snippet(mensaje);
		mapa.addMarker(marca);
		mapa.animateCamera(CameraUpdateFactory.newLatLng(ubicacion));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.taxi, menu);
		return true;
	}
	
	public void Inicializar(){
		CronometroTiempo = (Chronometer) findViewById(R.id.CronometroTiempo);
		et_Km = (TextView) findViewById(R.id.textViewkilo);
		et_$ = (TextView) findViewById(R.id.textViewCosto);
		et_Partida = (TextView) findViewById(R.id.textViewPartid);
		et_Llegada = (TextView) findViewById(R.id.textViewLlegad);
		button_O_O = (ToggleButton) findViewById(R.id.toggleButtonSat);
	 	mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMapas)).getMap();
 	}
	
	
	public void Limpiar(){
		et_Km.setText("");
		et_$.setText("");
		et_Partida.setText("");
		et_Llegada.setText("");
	} 
	
	public void ON_OFF(View v){				
		if(button_O_O.isChecked()){	// ON-----------------------------------------------------------------------------
			CronometroTiempo.start();
			Limpiar();
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			boolean gpsHabilitado = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
						
			if(!gpsHabilitado){
				Toast.makeText(this, "no provider habilitado, por favor habilite", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(intent);			
			}
			
			// SELECCIONAR UN PROVIDER
			if(gpsHabilitado){ proveedor = LocationManager.GPS_PROVIDER;}
			
			if(proveedor !=null){ //SI ESTÁ ACTIVO EL GPS, SE PODRÁ OBTENER LA LOCACLIZACIÓN
				locationI = locationManager.getLastKnownLocation(proveedor); 
			}else{
				Toast.makeText(this, "proveedor es nulo", Toast.LENGTH_SHORT).show();
			}
			
			if(locationI !=null){
				latitud_inicio = locationI.getLatitude();
				longitud_inicio = locationI.getLongitude();
				latlng = new LatLng(latitud_inicio, longitud_inicio);
				agregarMarca(latitud_inicio, longitud_inicio, "PUNTO DE PARTIDA", "Ubicación Inicio");	
				polilinea_options = new PolylineOptions().add(latlng).color(Color.RED);
				polilinea = mapa.addPolyline(polilinea_options);
				et_Partida.setText("LAT "+latitud_inicio+ " LONG "+longitud_inicio);
				DBTaximetro db = new DBTaximetro();
				Date hora =new Date();
				Integer v_hora = hora.getHours();
				tarifa = db.selectAllTarifa(this, v_hora);
				
				//ACTUALIZACIÓN DE LA LOCALIZACIÓN...PROVEEDOR, MILISEGUNDOS, METROS, ACTIVIDAD
				locationManager.requestLocationUpdates(proveedor, 250, 0, this);
			}else{
				Toast.makeText(this, "location es null", Toast.LENGTH_SHORT).show();
			}
			
		}else{
			// APAGADO OFF
			CronometroTiempo.stop();
			
			locationF = locationManager.getLastKnownLocation(proveedor);
			latitud_final = locationF.getLatitude();
			longitud_final = locationF.getLongitude();
			agregarMarca(latitud_final, longitud_final, "PUNTO DE LLEGADA", "Ubicación Final");
			//et_tiempo.setText(total_segundos/3600 + "h");
			et_Llegada.setText("LAT "+latitud_final+ " LONG "+longitud_final);
			et_Km.setText(distancia_total/1000+" Km");
			
			locationI = null;
			locationF=null;
			//removeUpdates -> detener nuevas actualizaciones
			locationManager.removeUpdates(this);
			CronometroTiempo.setBase(SystemClock.elapsedRealtime());
			
		}	
	}
	
	float total_segundos=0;
	
	public void onCancelar(View boton){
		Intent intent =new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	
	public boolean isEmpty(){
		if(et_Km.getText().toString().equals("")|| et_$.getText().toString().equals("") || et_Partida.getText().toString().equals("") || et_Llegada.getText().toString().equals("")){
			return true;
		}
		else{
			return false;
		}
	}
	
/*	public void onGuardar(View boton){
		Inicializar();
		
		
		DBTaximetro dbTaxi = new DBTaximetro();
    	if (isEmpty()){
    		Toast.makeText(this,"Algun(os) Campo(s) stán vacios!!", Toast.LENGTH_LONG).show();
    	}else{
	    		Carrera cr=new Carrera(0,et_Km.toString(), et_$.getText().toString(), et_Partida.getText().toString(), et_Llegada.getText().toString());
	    		dbTaxi.nuevaCarrera(this, cr.getIdCarrera(), cr.getIdPersonas(), cr.getIdTarifa(), cr.getKm(),cr.getValor(),cr.getOrigen(),cr.getCordenada_origen(),cr.getDestino(), cr.getCordenada_destino(),cr.getDia(),cr.getMes(),cr.getAnio());
	    		Toast.makeText(this,"Carrera Registrada exitosamente", Toast.LENGTH_SHORT).show();
	    		Limpiar();		
    	}	
	}*/
	
Double Tarifa_inicial;
	@Override
	public void onLocationChanged(Location location) {
		Inicializar();
		// TODO Auto-generated method stub
		//int latitud = (int) location.getLatitude();
				//int longitud = (int) location.getLongitude();
				//LatLng latlong = new LatLng(latitud, longitud);
				float velocidad = location.getSpeed();
				//int total_segundos_i=0, diferencia_segundos=0;
				
				if(velocidad > 0.0){
					Toast.makeText(this, "velocidad > 0", Toast.LENGTH_LONG).show();
					String tiempo_i = CronometroTiempo.getText().toString();
					int minutos_i = Integer.valueOf(tiempo_i.substring(0,2));
					int segundos_i = Integer.valueOf(tiempo_i.substring(3,5));
					Integer total_segundos_i = (minutos_i * 60) + segundos_i;
					Tarifa_inicial = tarifa.get(0).getArranque_tarifa();
					
					if(total_segundos == 10){
						Tarifa_inicial = Tarifa_inicial + 0.01;
					}
					if(distancia_total == 38){
						distancia_total = distancia_total + 0.01;
					}
				}
				else{
					if(velocidad == 0.0){
						//TARIFA DE PARADA
						Toast.makeText(this, "velocidad = 0", Toast.LENGTH_LONG).show();
						//cronometro.stop();
						//cronometro.setBase(diferencia_segundos);
						//cronometro.setBase(SystemClock.elapsedRealtime()-segundos_consumidos*1000);
						//String tiempo_parada= cronometro.getText().toString();
						//int minutos_p = Integer.valueOf(tiempo_parada.substring(0,2));
						//int segundos_p = Integer.valueOf(tiempo_parada.substring(3,5));
						//total_segundos_p = (minutos_p * 60) + segundos_p;
						//diferencia_segundos = total_segundos_p - total_segundos_i;
						//segundos_consumidos = segundos_consumidos + diferencia_segundos;
					}	
				}	
				total_segundos =  (float) (total_segundos + 0.25);
				distancia_total = distancia_total + (velocidad * 0.25);
				
				Toast.makeText(this, "VEL: " + velocidad +" m/s ...Seg: "+ total_segundos, Toast.LENGTH_LONG).show();	
				//total_segundos_p = 0;
				velocidad =(float) 0.0;
				et_Km.setText(distancia_total/1000+" Km");
				//PolylineOptions pol_options = new PolylineOptions().add(latlong).color(Color.RED);
				//Polyline polilinea2 = mapa.addPolyline(pol_options);
		
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

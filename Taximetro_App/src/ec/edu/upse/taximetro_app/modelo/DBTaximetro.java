package ec.edu.upse.taximetro_app.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBTaximetro {

	private static final String TABLA_NAME = "personas";
	private static final String TABLA_NAME1 = "usuarios";
	private static final String DB_NAME = "DBTaximetro";
	
	/*Inserta Usuario*/
	/*
	 * String sql = "CREATE TABLE personas (" +
			" id INTEGER PRIMARY KEY AUTOINCREMENT," +
			" nombres TEXT," +
			" apellidos TEXT," +
			" email TEXT," +
			" estado INTEGER )";
	 * */
	public void nuevoCliente(Context contexto, String nombres, String apellidos,
			String email, String usuario, String clave){
		Integer id_persona = 0;
		SqlTaximetro clienteDB= new SqlTaximetro(contexto, DB_NAME, null, 1);
		
		SQLiteDatabase db = clienteDB.getWritableDatabase();
		if (db!=null){
			db.execSQL("INSERT INTO "+TABLA_NAME +" VALUES (NULL,'" +nombres+ "', " +
					"'"+apellidos+"', '"+email+"','A')");
		}
		db.close();
		
		SqlTaximetro UserDB= new SqlTaximetro(contexto, DB_NAME, null, 1);
		SQLiteDatabase db2 = UserDB.getWritableDatabase();
		if (db2!=null){
			Cursor cursor = db2.rawQuery("SELECT MAX(id) FROM "+TABLA_NAME+"",null);
			if(cursor.moveToFirst()){
				//recorrer los resultados
				do{
					id_persona = cursor.getInt(0);
				}while(cursor.moveToNext());
			}	
		}
		db2.close();
	
		/*String sql1 = "CREATE TABLE usuarios (" +
				" id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" id_persona INTEGER NOT NULL" +
				" usuario TEXT," +
				" clave TEXT," +
				"FOREING KEY (id_persona) REFERENCES personas(id))";
		*/
		SqlTaximetro UserBd =new SqlTaximetro(contexto, DB_NAME, null, 1);
		SQLiteDatabase dbg = UserBd.getWritableDatabase();
		if(dbg!= null){
			dbg.execSQL("INSERT INTO "+TABLA_NAME1+" VALUES " +
					"(NULL,"+id_persona+", '"+usuario+"','"+clave+"','A')");
		}
		dbg.close();
	}
}
package ec.edu.upse.taximetro_app.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlTaximetro extends SQLiteOpenHelper {

	String sql = "CREATE TABLE personas (" +
			" id_p INTEGER PRIMARY KEY AUTOINCREMENT," +
			" nombres TEXT," +
			" apellidos TEXT," +
			" email TEXT," +
			" estado TEXT )";
	
	String sql1 = "CREATE TABLE usuarios (" +
			" id_u INTEGER PRIMARY KEY AUTOINCREMENT," +
			" id_persona INTEGER NOT NULL," +
			" usuario TEXT," +
			" clave TEXT," +
			" estado TEXT, " +
			"FOREIGN KEY (id_persona) REFERENCES personas(id_p))";
	
	public SqlTaximetro(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		db.execSQL("PRAGMA foreign_keys=ON;");
	}


	@Override
	public void onCreate(SQLiteDatabase sqldb) {
		// TODO Auto-generated method stub
		sqldb.execSQL(sql);
		sqldb.execSQL(sql1);
		
		sqldb.execSQL("CREATE TRIGGER fk_personas" +
			    " BEFORE" +
			    " INSERT ON usuarios FOR EACH ROW BEGIN"+
			    " SELECT CASE WHEN ((SELECT id_p FROM personas"+ 
			    " WHERE id_p=new.id_persona) IS NULL)"+
			    " THEN RAISE (ABORT,'Foreign Key Violation')" +
			    " END;"+
			    " END;");
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqldb, int arg1, int arg2) {
		// TODO Auto-generated method stub
		sqldb.execSQL("DROP TABLE IF EXISTS personas");
		sqldb.execSQL("DROP TABLE IF EXISTS usuarios");
	        //Se crea la nueva versión de la tabla
	        sqldb.execSQL(sql);
	        sqldb.execSQL(sql1);
	}

}

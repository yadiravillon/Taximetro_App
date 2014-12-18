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
	
	String sqlcarrera = "CREATE TABLE carrera (" +
			" id_c INTEGER PRIMARY KEY AUTOINCREMENT," +
			" id_personas INTEGER NOT NULL," +
			" id_tarifa INTEGER NOT NULL," +
			" km DOUBLE," +
			" valor DOUBLE," +
			" origen TEXT," +
			" coordenada_origen DOUBLE," +
			" destino TEXT," +
			" coordenada_destino DOUBLE," +
			" dia TEXT, " +
			" mes TEXT, " +
			" anio TEXT, " +
			"FOREIGN KEY (id_personas) REFERENCES personas(id_p), "+
			"FOREIGN KEY (id_tarifa) REFERENCES tarifa(id_t))";
	
	String sqltarifa = "CREATE TABLE tarifa (" +
			"id_t INTEGER PRIMARY KEY AUTOINCREMENT," +
			" descripcion TEXT," +
			" arranque_tarifa DOUBLE," +
			" km_recorrido DOUBLE," +
			" min_espera DOUBLE," +
			" carrera_min DOUBLE," +
			" estado TEXT )" ;
    
	
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
		sqldb.execSQL(sqlcarrera);
		sqldb.execSQL(sqltarifa);
		
		sqldb.execSQL("CREATE TRIGGER fk_personas" +
			    " BEFORE" +
			    " INSERT ON usuarios FOR EACH ROW BEGIN"+
			    " SELECT CASE WHEN ((SELECT id_p FROM personas"+ 
			    " WHERE id_p=new.id_persona) IS NULL)"+
			    " THEN RAISE (ABORT,'Foreign Key Violation')" +
			    " END;"+
			    " END;");
		
		
		sqldb.execSQL("CREATE TRIGGER fk_persona " +
			    " BEFORE INSERT ON carrera FOR EACH ROW BEGIN"+
			    " SELECT CASE WHEN ((SELECT p.id_p FROM personas p "+ 
			    " WHERE p.id_p=new.id_personas) IS NULL)"+
			    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
			    "  END;");
		
		sqldb.execSQL("CREATE TRIGGER fk_tarifa " +
			    " BEFORE INSERT ON carrera FOR EACH ROW BEGIN"+
			    " SELECT CASE WHEN ((SELECT t.id_t FROM tarifa t"+ 
			    " WHERE t.id_t=new.id_tarifa) IS NULL)"+
			    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
			    "  END;");
		
		
		
		sqldb.execSQL("INSERT INTO tarifa VALUES(NULL, 'Diurna',0.35,0.26,0.06,1.00,'A')");
		sqldb.execSQL("INSERT INTO tarifa VALUES(NULL, 'Nocturna',0.40,0.30,0.06,1.10,'A')");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqldb, int arg1, int arg2) {
		// TODO Auto-generated method stub
		sqldb.execSQL("DROP TABLE IF EXISTS tarifa");
		sqldb.execSQL("DROP TABLE IF EXISTS personas");
		sqldb.execSQL("DROP TABLE IF EXISTS usuarios");
		sqldb.execSQL("DROP TABLE IF EXISTS carrera");
	        //Se crea la nueva versión de la tabla
	        sqldb.execSQL(sql);
	        sqldb.execSQL(sql1);
	        sqldb.execSQL(sqltarifa);
	        sqldb.execSQL(sqlcarrera);
	}

}

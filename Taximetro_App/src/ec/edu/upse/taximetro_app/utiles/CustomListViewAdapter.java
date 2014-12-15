package ec.edu.upse.taximetro_app.utiles;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ec.edu.upse.taximetro_app.R;

public class CustomListViewAdapter extends ArrayAdapter<ItemConsulta> {
	Context context;
	 
    public CustomListViewAdapter(Context context, int resourceId, List<ItemConsulta> items) {
        super(context, resourceId, items);
        this.context = context;
    }
    
    private class ViewHolder {
        
        TextView txtRuta;
        TextView txtValor;
        TextView txtDistancia;      
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ItemConsulta item = getItem(position);
 // aqui inflamos el layout normal de un adapter es decir lo estmaos personalizando
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
        	// indicar con que layout vamos a inflar el listview
            convertView = mInflater.inflate(R.layout.activity_item__result, null);
            
            holder = new ViewHolder();
            holder.txtRuta = (TextView) convertView.findViewById(R.id.textView1Ruta);
            holder.txtValor = (TextView) convertView.findViewById(R.id.textViewValor);
            holder.txtDistancia = (TextView) convertView.findViewById(R.id.textViewDistancia);
            
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
 
        holder.txtRuta.setText(item.getRuta());
        holder.txtValor.setText(Double.toString( item.getValor()));
        holder.txtDistancia.setText(Double.toString(item.getDistancia()));
        
 
        return convertView;
    }
 
    
}


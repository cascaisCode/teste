package com.example.noticias;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Lista extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String menu[] = new String[]{"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menu);
		setListAdapter(adaptador);
		
		//setContentView(R.layout.activity_lista);
	}
	
	protected void OnlistItemclick(ListView l, View v, int position, long id){
		String aux = l.getItemAtPosition(position).toString();
        Toast.makeText(getBaseContext(), aux, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista, menu);
		return true;
	}

}

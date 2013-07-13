package com.example.noticias;


import java.util.List;

import com.example.persistence.DatabaseHelper;
import com.example.persistence.User;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore.Action;
import android.graphics.Bitmap;
import android.view.ActionMode;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("ShowToast") public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        RuntimeExceptionDao<User, Integer> simpleDao = getHelper().getUserDao();
		// query for all of the data objects in the database
		List<User> list = simpleDao.queryForAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void eventosair(View view){
    	finish();
        }
    
    public void eventolista(View view){
    	Intent itlista = new Intent(getBaseContext(), Lista.class);
    	startActivity(itlista);
        }
    
    public void eventotexto(View view){
	EditText edt1 = (EditText) findViewById(R.id.edt1);
	//String v1 = edt1.getText().toString();
	//String v1 = "";
	edt1.setText(" ");
    }
    
    public void eventofoto(View view){
    	Intent it1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
    	
    	//Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); 
        //it1.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
    	
    	startActivityForResult(it1, 100);
    }
    
    public void eventovideo(View view){
    	Intent it2 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE, null);
    	startActivityForResult(it2, 200);
    }
    
    public void eventoaudio(View view){
    	Intent it3 = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION, null);
    	startActivityForResult(it3, 300);
    }
    
    	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 100) {
                if (resultCode == RESULT_OK) {
                        Bitmap imagem = (Bitmap) data.getExtras().get("data");
                        Toast.makeText(this, "TIROU A FOTO", Toast.LENGTH_LONG).show();
                        ImageView foto = (ImageView) findViewById(R.id.foto);
                        foto.setImageBitmap(imagem);
                        //Toast.makeText(this, "Video saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
                }
                else if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "CANCELOU A FOTO", Toast.LENGTH_LONG).show();
                } 	
            }
            
            else if(requestCode == 200){
            	if (resultCode == RESULT_OK) {
                    //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    Toast.makeText(this, "GRAVOU O VIDEO", Toast.LENGTH_LONG).show();
            }
            	else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "CANCELOU O VIDEO", Toast.LENGTH_LONG).show();
            	}
            }
            
            else if(requestCode == 300){
            	if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "GRAVOU O AUDIO", Toast.LENGTH_LONG).show();
            }
            	else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "CANCELOU O AUDIO", Toast.LENGTH_LONG).show();
            	}
            }
            
            else {
            	Toast.makeText(this, "CODIGO DIFERENTE", Toast.LENGTH_LONG).show();
            }
      }
    	
    	
}
    


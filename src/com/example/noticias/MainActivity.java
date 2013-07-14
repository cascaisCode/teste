package com.example.noticias;


import com.example.persistence.Noticia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast") 
public class MainActivity extends Activity {
	
	private Uri myAudio;
	private Uri myVideo;
	private Uri myFoto;
	//private VideoView vv;
	//private ImageView iv;
	
	TextView valLatitude;
    TextView valLongitude;
    
    private Noticia noticia;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //vv = (VideoView) findViewById(R.id.bt_);
        //iv = (ImageView) findViewById(R.id.bt_foto);
        
        valLatitude = (TextView) findViewById(R.id.tv_lat);
        valLongitude = (TextView) findViewById(R.id.tv_long);
        
        noticia = new Noticia();
        
        localizar();

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
    	it2.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15);
    	it2.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
    	it2.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION,true);
    	startActivityForResult(it2, 200);
    }
    
    public void eventoaudio(View view){
    	Intent it3 = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION, null);
    	startActivityForResult(it3, 300);
    }
    
    	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 100) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "TIROU A FOTO", Toast.LENGTH_LONG).show();
                	myFoto = data.getData();
            		noticia.setUriFoto(myFoto);

                	//iv.setImageURI(myfoto);
                	
                    Bitmap imagem = (Bitmap) data.getExtras().get("data");
                    ImageView foto = (ImageView) findViewById(R.id.bt_foto);
                    foto.setImageBitmap(imagem);
                }
                else if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "CANCELOU A FOTO", Toast.LENGTH_LONG).show();
                } 	
            }
            
            else if(requestCode == 200){
            	if (resultCode == RESULT_OK) {
            		Toast.makeText(this, "GRAVOU O VIDEO", Toast.LENGTH_LONG).show();
                    myVideo = data.getData();
            		noticia.setUriVideo(myVideo);
            		//vv.setVideoURI(myVideo);
                    //vv.setMediaController(new MediaController(this));
                    //vv.requestFocus();
                    // vv.start(); 
            		//Uri myVideo = (Uri) data.getExtras().get("data");
            	}
            	else if(resultCode == RESULT_CANCELED){
            		Toast.makeText(this, "CANCELOU O VIDEO", Toast.LENGTH_LONG).show();
            	}
            }
            
            else if(requestCode == 300){
            	if (resultCode == RESULT_OK) {
                	Toast.makeText(this, "GRAVOU O AUDIO", Toast.LENGTH_LONG).show();
                	myAudio = data.getData();
                	noticia.setUriAudio(myAudio);
            	}
            	else if(resultCode == RESULT_CANCELED){
            		Toast.makeText(this, "CANCELOU O AUDIO", Toast.LENGTH_LONG).show();
            	}
            }
            
            else {
            	Toast.makeText(this, "CODIGO DIFERENTE", Toast.LENGTH_LONG).show();
            }
      }
    	
    /****   GPS    ****/
	public void Atualizar(Location location)
    {
    	Double latPoint = location.getLatitude();
    	Double lngPoint = location.getLongitude();
        
        valLatitude.setText("Latitude: "+latPoint.toString());
        valLongitude.setText("Longitude: "+lngPoint.toString());
    }
	
	public void localizar(){
		{
	    	LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    	
	    	LocationListener locationListener = new LocationListener() {
	    	    public void onLocationChanged(Location location) {
	    	      Atualizar(location);
	    	    }

	    	    public void onStatusChanged(String provider, int status, Bundle extras) {}

	    	    public void onProviderEnabled(String provider) {}

	    	    public void onProviderDisabled(String provider) {}
	    	  };

	    	  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	    }
	}
    	
    	
}
    


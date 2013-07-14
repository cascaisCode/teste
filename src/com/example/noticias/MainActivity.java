package com.example.noticias;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore.Action;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.view.ActionMode;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

@SuppressLint("ShowToast") public class MainActivity extends Activity {
	
	Uri myAudio;
	Uri myVideo;
	Uri myfoto;
	VideoView vv;
	ImageView iv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vv = (VideoView) findViewById(R.id.videoView1);
        iv = (ImageView) findViewById(R.id.foto);

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
                	myfoto = data.getData();
                	iv.setImageURI(myfoto);
                	
                    //Bitmap imagem = (Bitmap) data.getExtras().get("data");
                    //ImageView foto = (ImageView) findViewById(R.id.foto);
                    //foto.setImageBitmap(imagem);
                }
                else if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "CANCELOU A FOTO", Toast.LENGTH_LONG).show();
                } 	
            }
            
            else if(requestCode == 200){
            	if (resultCode == RESULT_OK) {
                    myVideo = data.getData();
            		Toast.makeText(this, "GRAVOU O VIDEO", Toast.LENGTH_LONG).show();
            		vv.setVideoURI(myVideo);
                    vv.setMediaController(new MediaController(this));
                    vv.requestFocus();
                    vv.start(); 
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
    


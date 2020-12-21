package com.example.testmd5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    EditText edit_enter_text;
    TextView android_md5_textv, ndk_md5_textv;
    String entereText;
    Button generate_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        edit_enter_text = findViewById( R.id.edit_enter_text );
        generate_btn=findViewById( R.id.generate_btn );
        android_md5_textv = findViewById( R.id.java_md5_textv );
        ndk_md5_textv = findViewById( R.id.ndk_md5_textv );


        generate_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entereText=edit_enter_text.getText().toString();

                //android
                android_md5_textv.setText( getMD5( entereText ) );
                Log.e( "ANDROID --", getMD5( entereText ) );
                android_md5_textv.setText( "ANDROID-"+ getMD5( entereText ));

                // ndk c
                ndk_md5_textv.setText( encryptByMD5( entereText ) );
                Log.e( "NDK --", encryptByMD5( entereText ) + "" );
                ndk_md5_textv.setText( "NDK-"+ encryptByMD5( entereText ));
            }
        } );


    }

    // java语言md5加密
    public String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance( "MD5" );
            md5.update( info.getBytes( "UTF-8" ) );
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString( 0xff & encryption[i] ).length() == 1) {
                    strBuf.append( "0" ).append( Integer.toHexString( 0xff & encryption[i] ) );
                } else {
                    strBuf.append( Integer.toHexString( 0xff & encryption[i] ) );
                }
            }

            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }


    // ndk c语言md5加密
    public native String encryptByMD5(String strText);

    static {
        System.loadLibrary( "CTestMD5" );
    }
}

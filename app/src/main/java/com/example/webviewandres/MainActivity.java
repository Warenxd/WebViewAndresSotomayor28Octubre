package com.example.webviewandres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos la referencia del Id definido en el XML
        webView = findViewById(R.id.webView);
        // Obtiene las configuraciones del WebView para modificar sus propiedades
        WebSettings webSettings = webView.getSettings();
        // Habilita el JavaScript
        webSettings.setJavaScriptEnabled(true);
        // Agrega una interfaz de JavaScript, permite comunicar el codigo de la aplicación.
        webView.addJavascriptInterface(new JavaScriptInterface(), "appInterface");
    }


    // EL Metodo loadURL se llama cuando el usuario hace click en el botón
    public void loadURL(View view){
        // Obtiene las referencias del editText definido en el XML
        EditText urlEditText = findViewById(R.id.urlEditText);

        // Obtiene el texto ingresado como una cadena
        String url = urlEditText.getText().toString();

        // Comprueba si la cadena está vacia
        if (url.isEmpty()){
            // Muestra el Toast indicando que se debe ingresar una URL valida
            Toast.makeText(this, "Ingresa una URL valida", Toast.LENGTH_SHORT).show();
        } else { // Si la cadena no está vacia carga la URL ingresada
            webView.loadUrl(url);
        }
    }

    // Define a clase interna que se utiliza para comunicar entre el JavaScript y Java
    private class JavaScriptInterface {
        // Clase interna que contiene un metodo llamado showtoast
        @android.webkit.JavascriptInterface // Permite a JavaScript llamarlo desde una pagina web
        public void showToast(String message){
        }
    }
}
package com.example.asus.androideventv1.utilidades;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase generalizada para consumo asi�ncrono de WebServices RestFull
 *
 * @author Cristian Timbi *
 */
@SuppressLint("NewApi")
public class ClienteRest extends AsyncTask<Object, Integer, Long> {

	public static final String TAG = "ClienteRest";

	//Escuchador de retorno en finalizacion de consumo
	private OnTaskCompleted listener;

	//Cadena de texto de respuesta producto de consumo de WS
	private String respStr;

	//Identificador de la solicitud enviada, para posterior referencia al momento de finalizacion
	private int idSolicitud;

	/**
	 * Relaci�n de listener para retorno de respuesta al consumo de WS
	 * @param listener
	 */
	public ClienteRest(OnTaskCompleted listener){
		this.listener=listener;
	}

	/**
	 * M�todo para invocaci�n/consumo de WS por solicitud GET
	 * @param url				//URL del WS a consumir
	 * @param params			//Conjunto de parametros pa el WS a concatenarse con la URL (ej: ?texto=a&numero=18)
	 * @param idSolicitud		//Indentificador de solicitud, para identiicaci�n al momento de respuesta al listener
	 * @param dialog			//Estado para indicar si se desea un Cuadro de dialog de espera mientras se ejecuta el WS o se lo haria en background
	 * @throws Exception        //Control de excepcion
	 */
	public void doGet(String url, String params, int idSolicitud, boolean dialog) throws Exception {
		if (dialog) {			//Llamada a cuadro de dialogo de Cargando
			showDialog();
		}
		this.idSolicitud = idSolicitud;

		if(params == null){
			params = "";
		}

		//Creaci�n de objeto HTTP para solicitud
		HttpGet httpGet = new HttpGet(url + params);
		httpGet.setHeader("content-type", "application/json");

		try {
			//Llamda a la ejecuci�n en bacground de solicitud HTTP
			this.execute("GET", httpGet);
		} catch (Exception ex) {
			Log.e(TAG, "Error en doGet " + url+params, ex);
			throw ex;
		}
	}

	/*
	 * M�todo para invocaci�n/consumo de WS por solicitud POST
	 * @param url				//URL del WS a consumir
	 * @param params			//Objeto o entidad a pasarse como parametro a metodo POST (ej: Persona)
	 * @param idSolicitud		//Indentificador de solicitud, para identiicaci�n al momento de respuesta al listener
	 * @param dialog			//Estado para indicar si se desea un Cuadro de dialog de espera mientras se ejecuta el WS o se lo haria en background
	 * @throws Exception        //Control de excepcion
	 */

	public void doPost(String url, Object param, int idSolicitud, boolean dialog) throws Exception {
		if (dialog) {		//Llamada a cuadro de dialogo de Cargando
			showDialog();
		}

		this.idSolicitud = idSolicitud;

		//Creaci�n de objeto HTTP para solicitud
		HttpPost post = new HttpPost(url);
		post.setHeader("content-type", "application/json");

		String paramJSON = "";
		try {
			// Construimos el objeto de parametro a formato JSON para ser pasado en la solicitud POST
			Gson gson = new Gson();
			paramJSON = gson.toJson(param);
			StringEntity entity = new StringEntity(paramJSON, HTTP.UTF_8);
			post.setEntity(entity);

			//Llamda a la ejecuci�n en bacground de solicitud HTTP
			this.execute("POST", post);
		} catch (Exception ex) {
			Log.e(TAG, "Error en doPost " + url + " --> " + paramJSON, ex);
			throw ex;
		}
	}

	/**
	 * Procedimiento que convierte texto plano de respuesta (formato JSON) de WS en una entidad o objeto Java
	 * por medio de la libreria GSON de google: https://code.google.com/p/google-gson/
	 * @param respuesta			Class de respuesta esperada, util para saber a que convertir
	 * @param <T>				Tipo Gen�rico de clase esperada como respuesta
	 * @return					Objeto POJO de respuesta
	 */
	@SuppressWarnings("unchecked")
	public <T> T getResult(@SuppressWarnings("rawtypes") Class respuesta){
		Log.i(TAG, "Convirtiendo respuesta del WS a objeto " + respuesta.toString() + ": " + respStr);
		GsonBuilder gsonBuilder = new GsonBuilder();
		//gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		T data = (T) gsonBuilder.create().fromJson(respStr, respuesta);
		return data;
	}

	/**
	 * Procedimiento que convierte texto plano de respuesta (formato JSON) de WS en una Coleccion
	 * de entidades o una coleccion de objetos Java --> List<T>
	 * por medio de la libreria GSON de google: https://code.google.com/p/google-gson/
	 * @param respuesta			Class de respuesta esperada, util para saber a que convertir
	 * @return					Colección de Objetos POJO de respuesta
	 */
	public <T> List<T> getResultList(Class<T> respuesta) {
		Object[] array = (Object[])java.lang.reflect.Array.newInstance(respuesta, 1);
		array = new Gson().fromJson(respStr, array.getClass());
		List<T> list = new ArrayList<T>();
		for (int i=0 ; i<array.length ; i++)
			list.add((T)array[i]);
		return list;
	}

	private ProgressDialog dialog;

	/**
	 * Procedimiento para llamada a Dialogo para notificaci�n de espera mistras se solicita WS
	 */
	public void showDialog(){
		try{
			dialog = new ProgressDialog((Context) listener);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setMessage("Cargando!!...");
			if(!dialog.isShowing()){
				dialog.show();
			}
		}catch(Exception e){}
	}

	/**
	 * Ejecuci�n de solicitud en background
	 * @param params
	 * @return
	 */
	@Override
	protected Long doInBackground(Object... params) {
		HttpClient httpClient = new DefaultHttpClient();

		String tipo = (String)params[0];

		if(tipo.equals("GET")){
			Log.i(TAG, "M�todo doInBackground para solicitud GET");
			HttpResponse resp;
			try {
				HttpGet get = (HttpGet) params[1];
				resp = httpClient.execute(get);

				int codigoRespuesta = resp.getStatusLine().getStatusCode();
				if(codigoRespuesta == 200){
					respStr = EntityUtils.toString(resp.getEntity(), HTTP.UTF_8);
					Log.i(TAG, "Respuesta a solicitud GET " + respStr);
				}else{
					Log.i(TAG, "C�digo de respuesta a solicitud no satisfactorio, codigo retornado " + codigoRespuesta);
					cancel(true);
				}
			} catch (Exception e) {
				Log.i(TAG, "Error en proceso doInBackgroud", e);
				cancel(true);
				//Cerrada de dialogo en caso de estar abierto (control de error null)
				try{dialog.dismiss();}catch(Exception e1){}
			}

		}else if(((String)params[0]).equals("POST")){
			Log.i(TAG, "Metodo doInBackground para solicitud POST");
			HttpResponse resp;
			try {
				HttpPost post = (HttpPost)params[1];
				resp = httpClient.execute(post);
				respStr = EntityUtils.toString(resp.getEntity(), HTTP.UTF_8);
				Log.i(TAG, "Respuesta en solicitud POST " + respStr);
			} catch (Exception e) {
				Log.i(TAG, "Error en proceso doInBackgroud", e);
				cancel(true);
				try{dialog.dismiss();}catch(Exception e1){}
			}
		}
		return null;
	}

	/**
	 * Procedimiento llamado automaticamente una vez finalizada el proceso en background, esto para
	 * notificaci�n a listener
	 * @param result		Respuesta desde doinBackground
	 */
	@Override
	protected void onPostExecute(Long result) {
		//Notificaci�n a evento listener de proceso finalizado
		listener.onTaskCompleted(idSolicitud);
		super.onPostExecute(result);
		//Cerrada de dialogo de espera
		try{dialog.dismiss();}catch(Exception e1){}
	}

	/**
	 * Procedimiento llamado en caso de error o cancelaci�n de proceso en background, esto para
	 * notificaci�n a listener
	 * @param result		Respuesta desde doinBackground
	 */
	@Override
	protected void onCancelled(Long result) {
		//Notificaci�n a evento listener de proceso finalizado
		listener.onTaskCompleted(idSolicitud);
		super.onCancelled(result);
		//Cerrada de dialogo de espera
		try{dialog.dismiss();}catch(Exception e1){}
	}

}

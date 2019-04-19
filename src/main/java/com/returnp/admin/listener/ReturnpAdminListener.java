package com.returnp.admin.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class ReturnpAdminListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		FileInputStream serviceAccount;
		try {
			 InputStream is = getClass().getResourceAsStream("/config.properties");
			 Properties props = new Properties();
			 props.load(is);

			 System.out.println("########################################################################");
			 System.out.println("### ReturnpAdminListener.contextInitialized");
			 System.out.println( "fcm_auth_file_path : " + props.getProperty("fcm_auth_file_path").trim());
			 System.out.println("########################################################################");
			 
			serviceAccount = new FileInputStream(props.getProperty("fcm_auth_file_path").trim());
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					//.setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
					.build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}

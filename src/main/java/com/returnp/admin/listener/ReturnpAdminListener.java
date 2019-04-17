package com.returnp.admin.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class ReturnpAdminListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("########################################################################");
		System.out.println("### ReturnpAdminListener.contextInitialized");
		System.out.println("########################################################################");
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("C:\\Users\\topayc\\returnp\\firebase\\returnp-fcm-firebase-adminsdk-7k3u6-fd6c0a5190.json");
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

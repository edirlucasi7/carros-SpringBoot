package com.carros.domain;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.carros.api.upload.UploadInput;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

@Service
public class FirebaseStorageService {

	@PostConstruct
	private void init() throws IOException {
		if(FirebaseApp.getApps().isEmpty()) {
			InputStream in = 
					FirebaseStorageService.class.getResourceAsStream("/serviceAccountKey.json");
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(in))
					  .setStorageBucket("carros-springboot-4d1f3.appspot.com")
					  .setDatabaseUrl("https://carros-springboot-4d1f3.firebaseio.com")
					  .build();

					FirebaseApp.initializeApp(options);
		}
	}
	
	public String upload(UploadInput uploadInput) {
		
		com.google.cloud.storage.Bucket bucket = StorageClient.getInstance().bucket();
		
		byte[] bytes = Base64.decodeBase64(uploadInput.getBase64());
		
		String fileName = uploadInput.getFileName();
		com.google.cloud.storage.Blob blob = bucket.create(fileName, bytes, uploadInput.getMimeType()); 
		
		// Assina URL válida por N dias
//		URL signedUrl = blob.signUrl(3, TimeUnit.DAYS);
		
		blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
		
		return String.format("http://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
	}
	
}

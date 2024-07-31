package mungmo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ConfigApplication.class, args);
	}

}

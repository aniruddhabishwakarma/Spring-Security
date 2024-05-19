package com.example.SpringSec.config1;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SSLHelper {

    public SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            /**
             * This is the method that we are interested in. It's called when the server sends its certificate.
             * We don't care about the server's certificate, we just want to trust it.
             */
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                Logger.getLogger(SSLHelper.class.getName()).log(Level.FINE,"Trusting all certificates");
                //check the certificate or do nothing
                if(certs.length > 0) {
                    Logger.getLogger(SSLHelper.class.getName()).log(Level.FINE,"Certificate subject");
                }
            }

            /**
             * This is the method that we are interested in. It's called when the server sends its certificate.
             * We don't care about the server's certificate, we just want to trust it.
             */
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                Logger.getLogger(SSLHelper.class.getName()).log(Level.FINE,"Trusting all certificates");
                //check the certificate or do nothing
                if(certs.length > 0) {
                    Logger.getLogger(SSLHelper.class.getName()).log(Level.FINE,"Certificate subject");
                }
            }
        }};
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create a SSL socket factory", e);
        }
    }
}

package com.hdi.hdi.util.Mail;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * @Description: 邮箱信证管理
 */
public class MailTrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] cert, String authType) {
        // everything is trusted
    }

    public void checkServerTrusted(X509Certificate[] cert, String authType) {
        // everything is trusted
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
package com.i.security

import android.content.res.AssetManager
import android.util.Base64
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

class SecurityHelper(val assetManager: AssetManager) {

    fun provideWithSSLContext(): SSLContext{
        val input = assetManager.open("example_client_keystore_tls.p12")
        val keyStore = KeyStore.getInstance("PKCS12")
        keyStore.load(input, "changeme".toCharArray())
        input.close()

        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, "changeme".toCharArray())

        val trustedStore = KeyStore.getInstance(KeyStore.getDefaultType())
        trustedStore.load(null)
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(trustedStore)
        val trustManagers = trustManagerFactory.trustManagers

        val sslContext = SSLContext.getInstance("TLS")

        sslContext.init(keyManagerFactory.keyManagers, trustManagers, null)

        return sslContext
    }

    fun readPrivateKeyFile(): PrivateKey {
        val input = assetManager.open("example_client_signing.key")
        val size = input.available()
        val buffer = ByteArray(size)
        input.read(buffer)
        input.close()
        val pem = String(buffer)
        val encoded = Base64.decode(pem, Base64.DEFAULT)
        val spec = PKCS8EncodedKeySpec(encoded)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(spec)
    }

    fun createSignature(stringToSign: String, privateKey: PrivateKey): String {
        val data = stringToSign.toByteArray(charset("UTF8"))
        val sig = Signature.getInstance("SHA256withRSA")
        sig.initSign(privateKey)
        sig.update(data)
        val signatureBytes = sig.sign()
        val base64Signature = Base64.encodeToString(signatureBytes, Base64.DEFAULT)
        return base64Signature
    }

    fun createDigest(text: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(text.toByteArray(StandardCharsets.UTF_8))
        return "SHA-256=${Base64.encodeToString(hash, Base64.DEFAULT)}"
    }
}
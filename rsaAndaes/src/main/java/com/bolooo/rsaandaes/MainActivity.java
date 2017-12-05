package com.bolooo.rsaandaes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class MainActivity extends AppCompatActivity {

    private TextView tvShow;
    String str = "hello world";
    private String encrypt;
    private String encryptAseKey;
    private RSAPrivateKey rsaPrivateKey;
    private String privateKey;
    private String publicKeyRSA= "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKaHb9LiPPo+XfabujiNvA/xoptjXKt6\n" +
            "AqPD9l30KcHGZbCnBuQeEGFcGPOsG0Wi1vBtxvdKJDbLKK8S1xSGFd8CAwEAAQ==";
    private String privateKeyRSA= "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEApodv0uI8+j5d9pu6\n" +
            "OI28D/Gim2Ncq3oCo8P2XfQpwcZlsKcG5B4QYVwY86wbRaLW8G3G90okNssorxLX\n" +
            "FIYV3wIDAQABAkEAl4wfhsGejnldV4Smtrq9j6Dtww1skxzI9DHsrdn6ej1Tbjbc\n" +
            "usZliJLliKdCjozwAVD/BfiJM3D5LbXQcJi8eQIhANsKPp4jzjmzq3WplcO1h1c9\n" +
            "yQk6VX8KJXFc2WaKPSkbAiEAwqDnX3FS0gjGrbjEMbSI0pPko9EWshgvWP1VqUHs\n" +
            "do0CIQDLSpnNOdwXz7pNrQRTrLIc9ToVd2+MRcV0jkEzmw+iiwIhAIfOguRETQgl\n" +
            "+4yI2s3cLYTSkb4FThLjcVZlH3rFd1yZAiAkyxFIThh5eAS4fGF4WeWTdl5wwUD6\n" +
            "/nCf2TAkasxGaQ==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = (TextView) findViewById(R.id.tv_show);
        TextView textView = (TextView) findViewById(R.id.tv_key);
        tvShow.setText(str);
        final String keyString = AES.generateKeyString();

        try {//公钥加密aes的私钥，传给服务器
            RSAPublicKey publicKey = RSA.loadPublicKey(publicKeyRSA);//生成公钥对象
            rsaPrivateKey = RSA.loadPrivateKey(privateKeyRSA);//生成私钥对象
            encryptAseKey = RSA.encryptByPublicKey(keyString, publicKey);//加密Aes的私钥
        } catch (Exception e) {
            e.printStackTrace();
        }

        textView.setText(keyString);
        findViewById(R.id.tv_ey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过ase私钥进行加密，传给服务器
                encrypt = AES.encrypt(str, keyString);
                Toast.makeText(MainActivity.this,encrypt,Toast.LENGTH_LONG).show();
                tvShow.setText(encrypt);
            }
        });
        findViewById(R.id.tv_de).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {//Rsa私钥解析ase私钥，通过ase私钥进行解密
                    privateKey = RSA.decryptByPrivateKey(encryptAseKey, rsaPrivateKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String decrypt = AES.decrypt(encrypt, privateKey);
                tvShow.setText(decrypt);
            }
        });
    }
}

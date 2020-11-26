package com.senses.bos.service.ws;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

@Slf4j
@Component
public class CryptSDK {
    @Value("${crypt.sdk.server.ip}")
    private String serverIp;

    @Value("${crypt.sdk.server.sdkpath}")
    private String strClassPath;

    public CryptSDK() {

    }

    public Resource cryptSDK(InputStream resource) throws IOException {
        File temp = temporaryFile(resource);
        String initString = String.format("<PARAMATERS><SERVERIP>%s</SERVERIP><ENCMAILPATH></ENCMAILPATH>" +
                "<DECMAILPATH></DECMAILPATH><INFOPATH>%s</INFOPATH></PARAMATERS>", serverIp, strClassPath);
        String filePath = temp.getAbsolutePath();
        String absolutePath = temp.getAbsolutePath();
        String tempFilePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
        String outfile = tempFilePath + "/" + RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890");
        String file = String.format("<PARAMATERS><FILEPATH>%s</FILEPATH><CLASSIFY_INFO></CLASSIFY_INFO></PARAMATERS>",
                filePath);
        String out = String.format("<PARAMATERS><OUTPATH>%s</OUTPATH></PARAMATERS>", outfile);

        loadSDK();
        int initCode = Init(initString);
        if (initCode == 0) {
            log.info("DRM init success");
            EncFile(file, out);
            Resource encResource = read(outfile);
            temp.delete();
            File cryptFile = new File(outfile);
            cryptFile.delete();
            Uninit();
            return encResource;
        } else {
            log.info("DRM init, code: " + initCode);
            Resource encResource = null;
            return encResource;
        }

    }


    public static native int Init(String jstrInitInfo);

    public static native int Uninit();

    // public native int DecFile(String jstrSendMailInfo, String jstrOutMailInfo);

    public static native int EncFile(String jstrSendMailInfo, String jstrOutMailInfo);

    public void loadSDK() {
        String jdkbit = System.getProperty("sun.arch.data.model");
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        String strClassPathSdk = null;
        if (os.startsWith("win") || os.startsWith("Win")) {
            if (jdkbit.equals("32")) {
                strClassPathSdk = strClassPath + "Windows/32/SDK_JNI.dll";
            } else if (jdkbit.equals("64")) {
                strClassPathSdk = strClassPath + "Windows/64/SDK_JNI_X64.dll";
            }
        } else {
            if (jdkbit.equals("32")) {
                strClassPathSdk = strClassPath + "Linux/32/Native.cws_cebsdk.so";
            } else if (jdkbit.equals("64")) {
                strClassPathSdk = strClassPath + "Linux/64/Native.cws_cebsdk.so";
            }
        }
        System.load(strClassPathSdk);
    }

    public File temporaryFile(InputStream inputStream) throws IOException {
        BufferedWriter bw = null;
        File temp = File.createTempFile("vqiTempFile", ".txt");
        try {
            log.info("Temp file created : " + temp.getAbsolutePath());

            OutputStream outputStream = new FileOutputStream(temp);
            byte[] bytes = IOUtils.toByteArray(inputStream);

            outputStream.write(bytes);
        } catch (IOException e) {
            log.error("Temp file created: ", e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    log.error("Temp file created: ", e);
                }

            }

        }
        return temp;
    }

    @SneakyThrows
    public Resource read(String fileName) {
        return new InputStreamResource(new FileInputStream(fileName));
    }
}

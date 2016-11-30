package com.example.martian.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by yangpei on 2016/12/1.
 */

public class NetUtil {

    private static final int HTTP_OK = 200;

    /**
     * HttpURLConnection  get
     *
     * @param uri
     * @return
     */
    public static String get(String uri) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(uri);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5 * 1000);
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setRequestProperty("Accept-Charset","UTF-8");
            httpURLConnection.connect();

            int code = httpURLConnection.getResponseCode();
            if (code == HTTP_OK) {
                return getStringFromInputStream(httpURLConnection.getInputStream());
            } else {
                return "请求失败code:" + code;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return null;
    }

    /**
     * HttpURLConnection  post
     *
     * @param uri
     * @param content
     * @return
     */
    public static String post(String uri, String content) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(uri);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setReadTimeout(5 * 1000);
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setDoOutput(true);

            OutputStream os = httpURLConnection.getOutputStream();
            os.write(content.getBytes());
            os.flush();
            os.close();

            httpURLConnection.connect();

            int code = httpURLConnection.getResponseCode();
            if (code == HTTP_OK) {
                return new String(getStringFromInputStream(httpURLConnection.getInputStream()));
            } else {
                return "请求失败code:" + code;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }


        return null;
    }

    /**
     * 从inoutstream中获取String
     *
     * @param is
     * @return
     * @throws IOException
     */
    private static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int length = 0;
        while ((length = is.read(data)) != -1) {
            os.write(data, 0, length);
        }
        is.close();
        String str =os.toString();
        os.close();

//        BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(is));
//        String line = "";
//        StringBuffer sb = new StringBuffer();
//        while ((line = bufferedReader.readLine() )!= null){
//            sb.append(line);
//        }
//        String str = sb.toString();
//        is.close();;
//        bufferedReader.close();;
        return  str;
    }

}

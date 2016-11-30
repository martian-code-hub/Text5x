package com.example.martian.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
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
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }


        return null;
    }

    /**
     * 上传文件（PPcall）
     *
     * @param unid
     * @param pic
     * @param api_token
     * @param pp_apiKey
     * @param api
     * @return
     */
    public static String upload(String unid, String pic, String api_token,
                                String pp_apiKey, String api) {

        HttpURLConnection httpURLConnection = null;
        String ppMD5 = MD5.Md5("{" + unid + "}" + "{" + api_token + "}" + "{"
                + pp_apiKey + "}");
        String imageURL = api + "?" + "unid=" + unid + "&ppMD5=" + ppMD5
                + "&api_token=" + api_token;
        String result = "";
        String end = "\r\n";
        String TwoH = "--";
        String boundary = "******";
        try {
            URL url = new URL(imageURL);
            httpURLConnection = (HttpURLConnection) url
                    .openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.writeBytes(TwoH + boundary + end);
            dos.writeBytes("Content-Disposition:form-data;name=\"pic\";filename=\""
                    + pic.substring(pic.lastIndexOf("/") + 1) + "\"" + end);
            dos.writeBytes(end);
            FileInputStream fis = new FileInputStream(new File(pic));
            byte[] buffer = new byte[8192];
            int count = 0;
            while ((count = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, count);
            }
            fis.close();
            dos.writeBytes(end);
            dos.writeBytes(TwoH + boundary + TwoH + end);
            dos.flush();

            dos.close();
            int code = httpURLConnection.getResponseCode();
            if (code == HTTP_OK) {
                return new String(getStringFromInputStream(httpURLConnection.getInputStream()));
            } else {
                return "请求失败code:" + code;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return result;
    }
/**  (例子)
 POST /api/feed/ HTTP/1.1
 Accept-Encoding: gzip
 Content-Length: 225873
 Content-Type: multipart/form-data; boundary=OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp
 Host: www.myhost.com
 Connection: Keep-Alive

 --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp
 Content-Disposition: form-data; name=param1
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit

 888
 --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp
 Content-Disposition: form-data; name=param2
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit

 nihao
 --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp
 Content-Disposition: form-data; name=images; filename=/storage/emulated/0/Camera/jdimage/1xh0e3yyfmpr2e35tdowbavrx.jpg
 Content-Type: application/octet-stream
 Content-Transfer-Encoding: binary

 这里是图片的二进制数据
 --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp--
 */
/**
 POST /api/feed/ HTTP/1.1
 Accept-Encoding: gzip
 Content-Length: 225873
 Content-Type: multipart/form-data; boundary=OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp
 Host: www.myhost.com
 Connection: Keep-Alive

 第一行：为POST方式，要请求的子路径为/api/feed/,例如我们的服务器地址为www.myhost.com,然后我们的这个请求的完整路径就是www.myhost.com/api/feed/，最后说明了HTTP协议的版本号为1.1
 第二行：数据压缩方式
 第三行：数据长度
 第四行：multipart/form-data;是指上传的数据类型，这里是指文件形式。boundary是我们必须指定的一个分界符，不同参数之间要用这个分界符隔开。而OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp就是具体的分界符，这个参数我们可以自己随机生成的。
 第五行：主机地址
 第六行：持久连接，Keep-Alive功能避免了建立或者重新建立连接
 第七行：换行，这个换行是必须的，我们使用 来进行换行
 */

/**
 --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp
 Content-Disposition: form-data; name=param1
 Content-Type: text/plain; charset=UTF-8
 Content-Transfer-Encoding: 8bit

 第一行：我要先用分隔符来声明一个参数的开始。注意，分隔符前面还加了两横“--”,这个也是必须加上的！
 第二行：name=param1，其实param1就是传递的参数的键值，例如在get方式中，我们这样写http://www.baidu.com?param1=888
 第三行：同样是内容格式，不过这次是指定传文本，所以是text/plain; 另外，指定了编码方式charset=UTF-8
 第四行：描述的是消息请求(request)和响应(response)所附带的实体对象(entity)的传输形式，简单文本数据我们设置为8bit，文件参数我们设置为binary就行
 第五行：换行，这个是必须的！
 第六行：参数值，例如http://www.baidu.com?param1=888，就是888
 */
    /**
     --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp
     Content-Disposition: form-data; name=images; filename=/storage/emulated/0/Camera/jdimage/1xh0e3yyfmpr2e35tdowbavrx.jpg
     Content-Type: application/octet-stream
     Content-Transfer-Encoding: binary

     这里是图片的二进制数据
     --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp--

     */
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 10000000; //超时时间
    private static final String CHARSET = "utf-8"; //设置编码
    private static final String PREFIX = "--";
    private static final String LINE_END = "\r\n";

    public static void upload(String host, File file, Map<String,String> params, FileUploadListener listener){
        String BOUNDARY = UUID.randomUUID().toString(); //边界标识 随机生成 String PREFIX = -- , LINE_END =
        ;
        String CONTENT_TYPE = "multipart/form-data"; //内容类型
        try {
            URL url = new URL(host);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setRequestMethod("POST"); //请求方式
            conn.setRequestProperty("Charset", CHARSET);//设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            conn.setDoInput(true); //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false); //不允许使用缓存
            if(file!=null) {
                /** * 当文件不为空，把文件包装并且上传 */
                OutputStream outputSteam=conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);
                StringBuffer sb = new StringBuffer();
                sb.append(LINE_END);
                if(params!=null){//根据格式，开始拼接文本参数
                    for(Map.Entry<String,String> entry:params.entrySet()){
                        sb.append(PREFIX).append(BOUNDARY).append(LINE_END);//分界符
                        sb.append("Content-Disposition: form-data; name=" + entry.getKey() +   LINE_END);
                        sb.append("Content-Type: text/plain; charset=" + CHARSET + LINE_END);
                        sb.append("Content-Transfer-Encoding: 8bit" + LINE_END);
                        sb.append(LINE_END);
                        sb.append(entry.getValue());
                        sb.append(LINE_END);//换行！
                    }
                }
                sb.append(PREFIX);//开始拼接文件参数
                sb.append(BOUNDARY); sb.append(LINE_END);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=img; filename="+file.getName()+LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="+CHARSET+LINE_END);
                sb.append(LINE_END);
                //写入文件数据
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                long totalbytes = file.length();
                long curbytes = 0;
                int len = 0;
                while((len=is.read(bytes))!=-1){
                    curbytes += len;
                    dos.write(bytes, 0, len);
                    listener.onProgress(curbytes,1.0d*curbytes/totalbytes);
                }
                is.close();
                dos.write(LINE_END.getBytes());//一定还有换行
                byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功
                 * 当响应成功，获取响应的流
                 */
                int code = conn.getResponseCode();
                sb.setLength(0);
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                listener.onFinish(code,sb.toString(),conn.getHeaderFields());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface FileUploadListener{
        public void onProgress(long pro,double precent);
        public void onFinish(int code,String res,Map<String,List<String>> headers);
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
        String str = os.toString();
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
        return str;
    }

}

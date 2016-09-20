package vip.xuanhao.integration.model.network.download;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import rx.Subscriber;

/**
 * Created by Xuanhao on 2016/8/30.
 */

public class FileDownload {

    private OkHttpClient okHttpClient;
    private Subscriber<? super FileInfo> subscriber;

    public FileDownload(@NotNull OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }


    public FileDownload(@NotNull OkHttpClient okHttpClient, @NotNull Subscriber<? super FileInfo> subscriber) {
        this.subscriber = subscriber;
        this.okHttpClient = okHttpClient;
    }

    public void setSubscriber(Subscriber<? super FileInfo> subscriber) {
        this.subscriber = subscriber;
    }


    /**
     * @param url
     * @param targetFilePath target file path
     * @throws IOException
     */
    public void download(@NotNull String url, @NotNull String targetFilePath) {
        if (subscriber == null) {
            throw new RuntimeException("you must call setSubscriber method before call download method or use FileDownload width two params");
        }

        if (!URLUtil.isNetworkUrl(url)) {
            throw new RuntimeException("invalid url");
        }
        Uri uri = Uri.parse(url);
        String fileName = uri.getLastPathSegment(); //文件名

        File filePath;

        if (!TextUtils.isEmpty(targetFilePath)) {
            filePath = new File(targetFilePath);
        } else {
            filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);//默认下载目录
        }
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File downloadFile = new File(filePath, fileName);
        if (downloadFile.exists()) {
            downloadFile.delete();
        }
        try {
            Request request = new Request.Builder().url(uri.toString()).build();
            Response response = okHttpClient.newCall(request).execute();

            ResponseBody body = response.body();
            String downType = body.contentType().type();
            long contentLength = body.contentLength();
            BufferedSource source = body.source();

            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setFileSize(contentLength);
            fileInfo.setFileType(downType);
            fileInfo.setFileUrl(uri.toString());
            BufferedSink sink = Okio.buffer(Okio.sink(downloadFile));
            Buffer buffer = sink.buffer();

            long total = 0;
            long len;
            int bufferSize = 200 * 1024; //200kb
            while ((len = source.read(buffer, bufferSize)) != -1) {
                sink.emit();
                total += len;
                fileInfo.setCurrentSize(total);
                if (subscriber != null)
                    subscriber.onNext(fileInfo);
                else
                    throw new RuntimeException("the subscriber instance must be not null");
            }
            sink.close();
            buffer.close();
            body.close();
            subscriber.onCompleted();
        } catch (IOException e) {
            subscriber.onError(e);
        }
    }


    public class FileInfo {

        private String fileName; //
        private String fileUrl;
        private String fileType;
        private long fileSize;//单位 byte
        private long currentSize;


        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

        public long getCurrentSize() {
            return currentSize;
        }

        public void setCurrentSize(long currentSize) {
            this.currentSize = currentSize;
        }
    }
}


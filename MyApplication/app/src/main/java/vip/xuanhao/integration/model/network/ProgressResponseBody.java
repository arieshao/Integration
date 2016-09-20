package vip.xuanhao.integration.model.network;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by Xuanhao on 2016/8/29.
 */

public class ProgressResponseBody extends ResponseBody {

    private DownLoadManager.ProgressListener progressListener;

    private ResponseBody responseBody;
    private BufferedSource bufferedSource;


    public ProgressResponseBody(DownLoadManager.ProgressListener progressListener, ResponseBody responseBody) {
        this.progressListener = progressListener;
        this.responseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {

        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }


    private Source source(Source source) {
        return new ForwardingSource(source) {

            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                progressListener.onProgress(contentLength(), totalBytesRead, bytesRead == -1);
                return bytesRead;
            }
        };
    }
}

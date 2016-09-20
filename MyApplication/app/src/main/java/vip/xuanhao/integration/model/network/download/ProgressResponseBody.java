package vip.xuanhao.integration.model.network.download;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

/**
 * Created by Xuanhao on 2016/8/30.
 */

public class ProgressResponseBody extends ResponseBody {


    private ResponseBody responseBody;

    private ProgressListener progressListener;

    private BufferedSource bufferedSource;

    public ProgressResponseBody() {

    }

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
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
            long total = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long read = super.read(sink, byteCount);
                total += read != -1 ? read : 0;
                progressListener.onProgress(contentLength(), total, read == -1);
                return read;
            }

            @Override
            public Timeout timeout() {
                return super.timeout();
            }

            @Override
            public void close() throws IOException {
                super.close();
            }
        };
    }


    public interface ProgressListener {
        void onProgress(long total, long progress, boolean complete);
    }

}


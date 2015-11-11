package com.wq.androidtest.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * 请求返回数据为Gson解析后的对象
 * 
 * @author Chen
 */
public class GsonRequest<T> extends Request<T> {
    public static final String TAG = GsonRequest.class.getSimpleName();
    private final Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;
    
    public GsonRequest(String url, Class<T> clazz, Listener<T> listener,
            ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }
    
    public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        mClass = clazz;
        mGson = new Gson();
    }
    
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            T parsedGson = mGson.fromJson(jsonString, mClass);
            return Response.success(parsedGson,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e1) {
            return Response.error(new ParseError(e1));
        }
    }
    
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
    
}

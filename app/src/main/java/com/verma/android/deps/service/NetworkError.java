package com.verma.android.deps.service;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class NetworkError extends Throwable {
    private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    private static final String NETWORK_ERROR_UBABLE_TO_CONNECT = "Unable to connect to the server! Please try again.";
    private static final String NETWORK_ERROR_MESSAGE = "No Internet Connection!";
    private static final String ERROR_MESSAGE_HEADER = "Error-Message";
    private static final String SSL_ERROR_MESSAGE = "Certificate not found.";
    private final Throwable error;

    public NetworkError(Throwable e) {
        super(e);
        this.error = e;
        Log.i("ERROR", e.toString());
    }

    @Override
    public String getMessage() {
        return error.getMessage();
    }

    public boolean isAuthFailure() {
        return error instanceof HttpException &&
                ((HttpException) error).code() == HTTP_UNAUTHORIZED;
    }

    public boolean isResponseNull() {
        return error instanceof HttpException && ((HttpException) error).response() == null;
    }

    public String getAppErrorMessage() {
        if (this.error instanceof SSLHandshakeException) return SSL_ERROR_MESSAGE;
        if (this.error instanceof NoRouteToHostException) return NETWORK_ERROR_UBABLE_TO_CONNECT;
        if (this.error instanceof SocketTimeoutException) return NETWORK_ERROR_UBABLE_TO_CONNECT;
        if (this.error instanceof ConnectException) return NETWORK_ERROR_UBABLE_TO_CONNECT;

        if (this.error instanceof IOException) return NETWORK_ERROR_MESSAGE;

        if (!(this.error instanceof HttpException)) return error.getMessage();
        retrofit2.Response<?> response = ((HttpException) this.error).response();
        if (response != null) {
            String status = getJsonStringFromResponse(response);
            if (!TextUtils.isEmpty(status)) return status;

            Map<String, List<String>> headers = response.headers().toMultimap();
            if (headers.containsKey(ERROR_MESSAGE_HEADER))
                return headers.get(ERROR_MESSAGE_HEADER).get(0);
        }

        return DEFAULT_ERROR_MESSAGE;
    }

    protected String getJsonStringFromResponse(final retrofit2.Response<?> response) {
        try {
            String jsonString = response.errorBody().string();
            Response errorResponse = new Gson().fromJson(jsonString, Response.class);
            return errorResponse.status;
        } catch (Exception e) {
            return null;
        }
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkError that = (NetworkError) o;

        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}

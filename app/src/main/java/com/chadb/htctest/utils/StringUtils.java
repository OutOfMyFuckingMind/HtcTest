package com.chadb.htctest.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StringUtils {

    private static final String tag = "StringUtils";

    public static String join(List<String> list) {
        return join(list, ", ");
    }

    public static String join(List<String> list, String separator) {

        if (list == null || list.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));

            if (i < list.size() - 1) {
                builder.append(separator);
            }
        }

        return builder.toString();
    }

    public static String readFromInputStream(InputStream inputStream) {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try {
            int result = bis.read();
            while(result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
        } catch (IOException exception) {
            Log.e(tag, "Error while read InputStream: " + exception.toString());
        }
        return buf.toString();
    }
}

package com.anymore.example.app.cookies;

import okhttp3.Cookie;
import timber.log.Timber;

import java.io.*;

public class SerializableCookie implements Serializable {

    private static final long serialVersionUID = -8594045714036645534L;


    private transient Cookie cookie;

    public String encode(Cookie cookie) {
        this.cookie = cookie;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            Timber.e(e, "IOException in encodeCookie");
            return null;
        } finally {
            if (objectOutputStream != null) {
                try {
                    // Closing a ByteArrayOutputStream has no effect, it can be used later (and is used in the return statement)
                    objectOutputStream.close();
                } catch (IOException e) {
                    Timber.e(e, "Stream not closed in encodeCookie");
                }
            }
        }

        return byteArrayToHexString(byteArrayOutputStream.toByteArray());
    }

    /**
     * Using some super basic byte array &lt;-&gt; hex conversions so we don't
     * have to rely on any large Base64 libraries. Can be overridden if you
     * like!
     *
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public Cookie decode(String encodedCookie) {

        byte[] bytes = hexStringToByteArray(encodedCookie);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                bytes);

        Cookie cookie = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((SerializableCookie) objectInputStream.readObject()).cookie;
        } catch (IOException e) {
            Timber.e(e, "IOException in decodeCookie");
        } catch (ClassNotFoundException e) {
            Timber.e(e, "ClassNotFoundException in decodeCookie");
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    Timber.e(e, "Stream not closed in decodeCookie");
                }
            }
        }
        return cookie;
    }

    /**
     * Converts hex values from strings to byte array
     *
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    private static long NON_VALID_EXPIRES_AT = -1L;

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(cookie.name());
        oos.writeObject(cookie.value());
        oos.writeLong(cookie.persistent() ? cookie.expiresAt() : NON_VALID_EXPIRES_AT);
        oos.writeObject(cookie.domain());
        oos.writeObject(cookie.path());
        oos.writeBoolean(cookie.secure());
        oos.writeBoolean(cookie.httpOnly());
        oos.writeBoolean(cookie.hostOnly());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Cookie.Builder builder = new Cookie.Builder();

        builder.name((String) ois.readObject());

        builder.value((String) ois.readObject());

        long expiresAt = ois.readLong();
        if (expiresAt != NON_VALID_EXPIRES_AT) {
            builder.expiresAt(expiresAt);
        }

        final String domain = (String) ois.readObject();
        builder.domain(domain);

        builder.path((String) ois.readObject());

        if (ois.readBoolean())
            builder.secure();

        if (ois.readBoolean())
            builder.httpOnly();

        if (ois.readBoolean())
            builder.hostOnlyDomain(domain);

        cookie = builder.build();
    }

}
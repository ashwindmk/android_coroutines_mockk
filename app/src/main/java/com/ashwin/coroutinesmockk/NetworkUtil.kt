package com.ashwin.coroutinesmockk

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

fun getResponse(reqUrl: String): String? {
    // Maintain http url connection.
    var httpConn: HttpURLConnection? = null

    // Read text input stream.
    var isReader: InputStreamReader? = null

    // Read text into buffer.
    var bufReader: BufferedReader? = null

    // Save server response text.
    val readTextBuf = StringBuffer()

    try {
        // Create a URL object use page url.
        val url = URL(reqUrl)

        // Open http connection to web server.
        httpConn = url.openConnection() as HttpURLConnection

        // Set http request method to get.
        httpConn!!.requestMethod = "GET"

        // Set connection timeout and read timeout value.
        httpConn!!.connectTimeout = 10000
        httpConn!!.readTimeout = 10000

        // Get input stream from web url connection.
        val inputStream = httpConn!!.inputStream

        // Create input stream reader based on url connection input stream.
        isReader = InputStreamReader(inputStream)

        // Create buffered reader.
        bufReader = BufferedReader(isReader)

        // Read line of text from server response.
        var line = bufReader!!.readLine()

        // Loop while return line is not null.
        while (line != null) {
            // Append the text to string buffer.
            readTextBuf.append(line)

            // Continue to read text line.
            line = bufReader!!.readLine()
        }

        return readTextBuf.toString()
    } catch (ex: MalformedURLException) {
        Log.e("debug-log", ex.message, ex)
    } catch (ex: IOException) {
        Log.e("debug-log", ex.message, ex)
    } finally {
        try {
            if (bufReader != null) {
                bufReader!!.close()
                bufReader = null
            }

            if (isReader != null) {
                isReader!!.close()
                isReader = null
            }

            if (httpConn != null) {
                httpConn!!.disconnect()
                httpConn = null
            }
        } catch (ex: IOException) {
            Log.e("debug-log", ex.message, ex)
        }
    }

    return null
}

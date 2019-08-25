package com.home.android.piperbike.views.utils

import android.content.Context
import android.util.Base64
import android.util.Log
import com.home.android.piperbike.PiperbikeApp
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.GeneralSecurityException

object EncryptionUtils {

    private const val DEFAULT_ENCODING = "UTF-8"
    private const val ASSOCIATED_DATA = "ASSOCIATED_DATA"
    private val CIPHER = ASSOCIATED_DATA.toByteArray(Charset.forName(DEFAULT_ENCODING))

    private val TAG = EncryptionUtils.javaClass.simpleName

    fun encrypt(data: String, appContext: Context): String {
        try {
            val app = appContext as PiperbikeApp
            val dataBytes = data.toByteArray(Charset.forName(DEFAULT_ENCODING))
            return base64Encode(app.aead.encrypt(dataBytes, CIPHER))
        } catch (ex: Exception) {
            when (ex) {
                is UnsupportedEncodingException,
                is GeneralSecurityException,
                is IllegalArgumentException -> {
                    Log.d(TAG, "Error during encryption: ${ex.localizedMessage}")
                } else -> { throw ex }
            }
        }

        throw IllegalArgumentException("Could not encrypt value!")
    }

    fun decrypt(data: String, appContext: Context): String {
        try {
            val app = appContext as PiperbikeApp

            val cipherBytes = base64Decode(data)
            return String(
                app.aead.decrypt(cipherBytes, CIPHER),
                Charset.forName(DEFAULT_ENCODING)
            )
        } catch (ex: Exception) {
            when (ex) {
                is UnsupportedEncodingException,
                is GeneralSecurityException,
                is IllegalArgumentException -> {
                    Log.d(TAG, "Error during decryption: ${ex.localizedMessage}")
                } else -> { throw ex }
            }
        }

        throw IllegalArgumentException("Could not decrypt value!")
    }

    private fun base64Encode(input: ByteArray): String {
        return Base64.encodeToString(input, Base64.DEFAULT)
    }

    private fun base64Decode(input: String): ByteArray {
        return Base64.decode(input, Base64.DEFAULT)
    }
}
package com.home.android.piperbike

import android.app.Application
import com.google.crypto.tink.Aead
import com.google.crypto.tink.Config
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadFactory
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.config.TinkConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import java.io.IOException
import java.security.GeneralSecurityException

class TinkApp : Application() {
    companion object {
        private val TAG = TinkApp::class.java.name
        private const val PREF_FILE_NAME = "profile_pref"
        private const val TINK_KEYSET_NAME = "profile_keyset"
        private const val MASTER_KEY_URI = "android-keystore://profile_master_key"
    }

    lateinit var aead: Aead

    override fun onCreate() {
        super.onCreate()

        try {
            Config.register(TinkConfig.TINK_1_0_0)
            aead = AeadFactory.getPrimitive(getOrGenerateNewKeysetHandle())
        } catch (e: GeneralSecurityException) {
            throw RuntimeException(e);
        }
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    private fun getOrGenerateNewKeysetHandle(): KeysetHandle {
        return AndroidKeysetManager.Builder()
            .withSharedPref(applicationContext, TINK_KEYSET_NAME, PREF_FILE_NAME)
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri(MASTER_KEY_URI)
            .build()
            .keysetHandle
    }
}
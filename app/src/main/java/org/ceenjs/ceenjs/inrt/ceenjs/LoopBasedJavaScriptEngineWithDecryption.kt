package org.ceenjs.ceenjs.inrt.ceenjs

import android.content.Context
import org.ceenjs.ceenjs.engine.LoopBasedJavaScriptEngine
import org.ceenjs.ceenjs.engine.encryption.ScriptEncryption
import org.ceenjs.ceenjs.pio.PFiles
import org.ceenjs.ceenjs.script.EncryptedScriptFileHeader
import org.ceenjs.ceenjs.script.EncryptedScriptFileHeader.BLOCK_SIZE
import org.ceenjs.ceenjs.script.JavaScriptFileSource
import org.ceenjs.ceenjs.script.ScriptSource
import org.ceenjs.ceenjs.script.StringScriptSource

class LoopBasedJavaScriptEngineWithDecryption(context: Context) : LoopBasedJavaScriptEngine(context) {

    override fun execute(source: ScriptSource?, callback: ExecuteCallback?) {
        if (source is JavaScriptFileSource) {
            try {
                val file = source.file
                val bytes = PFiles.readBytes(file.path)
                if (EncryptedScriptFileHeader.isValidFile(bytes)) {
                    super.execute(StringScriptSource(file.name, String(ScriptEncryption.decrypt(bytes, BLOCK_SIZE))), callback)
                } else {
                    super.execute(source, callback)
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        } else {
            super.execute(source, callback)
        }
    }

}
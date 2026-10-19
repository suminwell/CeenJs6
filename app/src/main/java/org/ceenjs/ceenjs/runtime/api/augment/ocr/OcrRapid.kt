package org.ceenjs.ceenjs.runtime.api.augment.ocr

import org.ceenjs.ceenjs.annotation.RhinoRuntimeFunctionInterface
import org.ceenjs.ceenjs.apkbuilder.ApkBuilder
import org.ceenjs.ceenjs.core.image.ImageWrapper
import org.ceenjs.ceenjs.rhino.ArgumentGuards
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.OcrResult
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.api.augment.Invokable
import org.ceenjs.ceenjs.runtime.api.augment.ocr.Ocr.Companion.OcrMode
import org.mozilla.javascript.NativeArray
import org.mozilla.javascript.NativeObject

class OcrRapid(private val scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime), Invokable {

    override val key = OcrMode.RAPID.value

    override val selfAssignmentFunctions = listOf(
        ::recognizeText.name,
        ::detect.name,
    )

    override fun invoke(vararg args: Any?): NativeArray = recognizeText(scriptRuntime, args)

    companion object : ArgumentGuards() {

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun recognizeText(scriptRuntime: ScriptRuntime, args: Array<out Any?>): NativeArray = ensureArgumentsAtMost(args, 3) { argList ->
            Ocr.recognizeTextWith(scriptRuntime, OcrMode.RAPID, argList)
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun detect(scriptRuntime: ScriptRuntime, args: Array<out Any?>): NativeArray = ensureArgumentsAtMost(args, 3) { argList ->
            Ocr.detectWith(scriptRuntime, OcrMode.RAPID, argList)
        }

        // @Hint by SuperMonster003 on Nov 1, 2024.
        //  ! Reserved param `options`.
        //  ! zh-CN: 预留参数 `options`.
        fun recognizeTextImpl(scriptRuntime: ScriptRuntime, image: ImageWrapper, @Suppress("UNUSED_PARAMETER") options: NativeObject): List<String> {
            ApkBuilder.Lib.RAPID_OCR.ensureLibFiles(OcrMode.RAPID.value)
            return scriptRuntime.ocrRapid.recognizeText(image)
        }

        // @Hint by SuperMonster003 on Nov 1, 2024.
        //  ! Reserved param `options`.
        //  ! zh-CN: 预留参数 `options`.
        fun detectImpl(scriptRuntime: ScriptRuntime, image: ImageWrapper, @Suppress("UNUSED_PARAMETER") options: NativeObject): List<OcrResult> {
            ApkBuilder.Lib.RAPID_OCR.ensureLibFiles(OcrMode.RAPID.value)
            return scriptRuntime.ocrRapid.detect(image)
        }

    }

}
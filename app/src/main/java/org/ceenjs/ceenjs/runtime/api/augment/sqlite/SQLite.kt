package org.ceenjs.ceenjs.runtime.api.augment.sqlite

import org.ceenjs.ceenjs.annotation.RhinoRuntimeFunctionInterface
import org.ceenjs.ceenjs.core.database.Database
import org.ceenjs.ceenjs.core.database.Database.DatabaseCallback
import org.ceenjs.ceenjs.rhino.ArgumentGuards
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.isJsNullish
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.jsBrief
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.jsSanitize
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.jsTryToJava
import org.ceenjs.ceenjs.rhino.extension.ScriptableObjectExtensions.inquire
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.SQLite.Companion.DEFAULT_READ_ONLY
import org.ceenjs.ceenjs.runtime.api.SQLite.Companion.DEFAULT_VERSION
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.api.augment.Invokable
import org.ceenjs.ceenjs.util.RhinoUtils.coerceBoolean
import org.ceenjs.ceenjs.util.RhinoUtils.coerceIntNumber
import org.ceenjs.ceenjs.util.RhinoUtils.coerceString
import org.ceenjs.ceenjs.util.RhinoUtils.newNativeObject
import org.mozilla.javascript.NativeObject

class SQLite(private val scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime), Invokable {

    override val key = super.key.lowercase()

    override val selfAssignmentFunctions = listOf(
        ::open.name,
    )

    override fun invoke(vararg args: Any?): Database = ensureArgumentsLengthInRange(args, 1..3) { argList ->
        open(scriptRuntime, argList)
    }

    companion object : ArgumentGuards() {

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun open(scriptRuntime: ScriptRuntime, args: Array<out Any?>): Database = ensureArgumentsLengthInRange(args, 1..3) { argList ->
            val (databaseFilePath, options, callback) = argList

            val filePath = coerceString(databaseFilePath, "")
            require(filePath.isNotEmpty()) { "Argument databaseFilePath of sqlite.open() must not be empty" }

            val niceOptions = if (options.isJsNullish()) newNativeObject() else options
            require(niceOptions is NativeObject) { "Argument \"options\" ${options.jsBrief()} for sqlite.open() must be a JavaScript Object" }

            val niceCallback = callback.jsSanitize()?.jsTryToJava<DatabaseCallback>()
            require(niceCallback is DatabaseCallback?) { "Argument \"callback\" ${callback.jsBrief()} for sqlite.open() must be a DatabaseCallback" }

            scriptRuntime.sqlite.open(
                databaseFilePath = filePath,
                version = niceOptions.inquire("version", ::coerceIntNumber, DEFAULT_VERSION),
                readOnly = niceOptions.inquire("readOnly", ::coerceBoolean, DEFAULT_READ_ONLY),
                callback = niceCallback,
            )
        }

    }

}

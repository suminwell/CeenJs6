package org.ceenjs.ceenjs.runtime.api.augment.files

import org.ceenjs.ceenjs.annotation.RhinoRuntimeFunctionInterface
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.isJsNullish
import org.ceenjs.ceenjs.rhino.ArgumentGuards
import org.ceenjs.ceenjs.pio.PFileInterface
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.exception.ShouldNeverHappenException
import org.ceenjs.ceenjs.util.RhinoUtils.coerceIntNumber
import org.mozilla.javascript.Context
import java.io.File
import org.ceenjs.ceenjs.runtime.api.Files as ApiFiles

@Suppress("unused", "UNUSED_PARAMETER")
class Files(scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime) {

    override val selfAssignmentFunctions = listOf(
        ::open.name to AS_GLOBAL,
        ::path.name,
        ::join.name,
        ::toFile.name,
    )

    companion object : ArgumentGuards() {

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun open(scriptRuntime: ScriptRuntime, args: Array<out Any?>): PFileInterface = ensureArgumentsLengthInRange(args, 1..4) {
            when (it.size) {
                1 -> scriptRuntime.files.open(Context.toString(it[0]))
                2 -> scriptRuntime.files.open(Context.toString(it[0]), Context.toString(it[1]))
                3 -> scriptRuntime.files.open(Context.toString(it[0]), Context.toString(it[1]), Context.toString(it[2]))
                4 -> scriptRuntime.files.open(Context.toString(it[0]), Context.toString(it[1]), Context.toString(it[2]), coerceIntNumber(it[3]))
                else -> throw ShouldNeverHappenException()
            }
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun path(scriptRuntime: ScriptRuntime, args: Array<out Any?>): String? = ensureArgumentsAtMost(args, 1) { argList ->
            val (o) = argList
            when {
                o.isJsNullish() -> scriptRuntime.files.path(null)
                else -> scriptRuntime.files.path(Context.toString(o))
            }
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun join(scriptRuntime: ScriptRuntime, args: Array<out Any?>): String = ensureArgumentsAtLeast(args, 1) {
            ApiFiles.join(Context.toString(it[0]), *it.drop(1).map { arg -> Context.toString(arg) }.toTypedArray())
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun toFile(scriptRuntime: ScriptRuntime, args: Array<out Any?>): File = ensureArgumentsOnlyOne(args) {
            File(scriptRuntime.files.nonNullPath(Context.toString(it)))
        }

    }

}
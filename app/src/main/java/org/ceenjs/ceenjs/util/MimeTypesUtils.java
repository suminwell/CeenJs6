package org.ceenjs.ceenjs.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.ceenjs.ceenjs.annotation.ReservedForCompatibility;
import org.ceenjs.ceenjs.runtime.api.Mime;

/**
 * Created by Stardust on Feb 12, 2018.
 * Modified by SuperMonster003 as of Apr 11, 2024.
 */
@ReservedForCompatibility
public class MimeTypesUtils {

    @Nullable
    @ReservedForCompatibility
    public static String fromFile(String path) {
        return Mime.fromFile(path);
    }

    @NonNull
    @ReservedForCompatibility
    public static String fromFileOr(String path, @Nullable String defaultType) {
        return Mime.fromFileOr(path, defaultType);
    }

}

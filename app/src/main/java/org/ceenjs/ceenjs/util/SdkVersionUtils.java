package org.ceenjs.ceenjs.util;

import org.ceenjs.ceenjs.runtime.api.augment.util.VersionCodesInfoGenerated;

/**
 * Created by Stardust on Apr 3, 2017.
 * Modified by SuperMonster003 as of Feb 5, 2022.
 */
public class SdkVersionUtils {

    public static String sdkIntToString(int i) {
        for (int j = 0; j < VersionCodesInfoGenerated.list.size(); j++) {
            var versionCodesInfo = VersionCodesInfoGenerated.list.get(j);
            if (versionCodesInfo.getApiLevel().equals(String.valueOf(i))) {
                return versionCodesInfo.getPlatformVersion();
            }
        }
        return "Unknown";
    }

}

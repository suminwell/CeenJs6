package org.ceenjs.ceenjs.project

import com.google.gson.annotations.SerializedName
import org.ceenjs.ceenjs.annotation.SerializedNameCompatible
import org.ceenjs.ceenjs.annotation.SerializedNameCompatible.With

data class ScriptConfig @JvmOverloads constructor(
    @SerializedName("useFeatures")
    @field:SerializedNameCompatible(
        With(value = "useFeature"),
        With(value = "useFeatureList"),
        With(value = "features"),
        With(value = "feature"),
        With(value = "featureList"),
    )
    var features: List<String> = emptyList(),
) {

    fun hasFeature(feature: String) = features.contains(feature)

    companion object {
        const val FEATURE_CONTINUATION = "continuation"
    }

}
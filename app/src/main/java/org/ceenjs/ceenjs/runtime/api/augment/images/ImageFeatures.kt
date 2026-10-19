package org.ceenjs.ceenjs.runtime.api.augment.images

import org.ceenjs.ceenjs.core.image.Shootable
import org.ceenjs.ceenjs.runtime.api.ImageFeatureMatching
import org.ceenjs.ceenjs.util.RhinoUtils
import org.mozilla.javascript.Undefined
import org.opencv.core.Rect

class ImageFeatures(
    @JvmField var javaObject: ImageFeatureMatching.FeatureMatchingDescriptor,
    @JvmField var scale: Float,
    @JvmField var region: Rect,
) : Shootable<ImageFeatures> {

    private var mIsOneShot = false

    @JvmField
    var recycled = false

    override fun isRecycled() = recycled

    override fun setOneShot(b: Boolean): ImageFeatures {
        mIsOneShot = b
        return this
    }

    override fun shoot() {
        if (mIsOneShot) recycle()
    }

    @JvmField
    var onRecycled: ((ImageFeatures) -> Undefined) = { _: ImageFeatures -> RhinoUtils.UNDEFINED }

    override fun recycle() {
        if (!recycled) {
            javaObject.release()
            onRecycled.invoke(this)
            recycled = true
        }
    }

}
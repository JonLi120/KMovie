package com.messon.project.kmovie.ui.tooling

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(
  name = "tablet",
  device = Devices.TABLET,
  showSystemUi = true,
  showBackground = true
)
@Preview(
  name = "landscape",
  device = "spec:parent=pixel_9_pro,orientation=landscape",
  showSystemUi = true,
  showBackground = true
)
@Preview(
  name = "phone-light",
  showBackground = true,
  showSystemUi = true
)
@Preview(
  name = "phone-dark",
  showBackground = true,
  uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
  showSystemUi = true
)
annotation class DevicePreviews

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(
  name = "phone-light",
  showBackground = true,
)
@Preview(
  name = "phone-dark",
  showBackground = true,
  uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
annotation class UiModePreviews
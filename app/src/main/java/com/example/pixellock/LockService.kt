package com.example.pixellock

import android.accessibilityservice.AccessibilityService
import android.os.VibrationAttributes
import android.os.VibrationEffect
import android.os.VibratorManager
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent

class LockService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onUnbind(intent: android.content.Intent?): Boolean {
        instance = null
        return super.onUnbind(intent)
    }

    // Volume up locks the screen. The key is consumed, so it never changes the volume.
    override fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode != KeyEvent.KEYCODE_VOLUME_UP) return false
        if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) lock()
        return true
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) = Unit

    override fun onInterrupt() = Unit

    // Accessibility usage so the buzz isn't suppressed as the screen turns off.
    private fun buzz() {
        val vibrator = getSystemService(VibratorManager::class.java).defaultVibrator
        vibrator.vibrate(
            VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK),
            VibrationAttributes.createForUsage(VibrationAttributes.USAGE_ACCESSIBILITY)
        )
    }

    companion object {
        @Volatile
        var instance: LockService? = null

        /** Returns false if the accessibility service isn't enabled yet. */
        fun lock(): Boolean {
            val service = instance ?: return false
            service.buzz()
            return service.performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
        }
    }
}

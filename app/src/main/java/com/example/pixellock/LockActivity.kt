package com.example.pixellock

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class LockActivity : Activity() {

    // onResume (not onCreate) so a lingering instance still locks on every tap.
    override fun onResume() {
        super.onResume()
        if (!LockService.lock()) {
            Toast.makeText(this, R.string.enable_hint, Toast.LENGTH_LONG).show()
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }
        finishAndRemoveTask()
    }
}

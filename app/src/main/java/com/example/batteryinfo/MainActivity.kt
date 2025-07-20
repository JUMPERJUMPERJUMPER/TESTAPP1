
package com.example.batteryinfo

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val batteryLevelText: TextView = findViewById(R.id.batteryLevelText)
        val batteryCapacityText: TextView = findViewById(R.id.batteryCapacityText)

        val batteryStatus = registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val batteryPct = level * 100 / scale

        batteryLevelText.text = "Akkustand: $batteryPct%"

        val mBatteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
        val capacity = mBatteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER) / 1000
        batteryCapacityText.text = "Kapazität: ${capacity} mAh"
    }
}

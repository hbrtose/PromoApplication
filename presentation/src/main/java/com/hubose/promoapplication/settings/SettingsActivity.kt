package com.hubose.promoapplication.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.hubose.promoapplication.R
import androidx.preference.SeekBarPreference

class SettingsActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SettingsFragment())
            .commit()
    }

    class SettingsFragment: PreferenceFragmentCompat(){
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preference, rootKey)
            val preference = findPreference<SeekBarPreference>("time")
            preference?.max = 10
            preference?.min = 6
            preference?.summary = String.format(getString(R.string.preference_desc), preference?.value as Int)
            preference.setOnPreferenceChangeListener { pref, newValue ->
                pref.summary = String.format(getString(R.string.preference_desc), newValue as Int)
                return@setOnPreferenceChangeListener true
            }
        }
    }
}
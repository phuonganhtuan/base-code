package com.example.demomotion.demo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.example.demomotion.MainActivity
import com.example.demomotion.MainViewModel
import com.example.demomotion.base.BaseActivity
import com.example.demomotion.databinding.ActivityPermissionBinding
import com.example.demomotion.utils.gone
import com.example.demomotion.utils.show

class PermissionActivity : BaseActivity<ActivityPermissionBinding, MainViewModel>() {

    override fun inflateViewBinding() = ActivityPermissionBinding.inflate(layoutInflater)

    override fun onActivityReady() {
        viewBinding.buttonSetting.setOnClickListener {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        }
        viewBinding.buttonRequest.setOnClickListener {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                viewBinding.textTitle.show()
                viewBinding.buttonSetting.show()
                viewBinding.textRequest.gone()
                viewBinding.buttonRequest.gone()
            }
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            100 -> {
                if ((grantResults.isNotEmpty()) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        viewBinding.textTitle.show()
                    }
                }
            }
        }
    }
}

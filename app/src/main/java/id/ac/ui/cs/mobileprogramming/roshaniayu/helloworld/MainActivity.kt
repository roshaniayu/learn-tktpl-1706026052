package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val wifiList = mutableListOf<WifiModel>()
    private lateinit var wifiManager: WifiManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var retrofit: ApiService
    private lateinit var sendButton: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        wifiManager = applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (!checkIfAlreadyHavePermission()) {
            requestForSpecificPermission();
        } else{
            startWifiScanning()
        }

        retrofit = RetrofitClient.RETROFIT_SERVICE
        sendButton = findViewById(R.id.send_button)
        sendButton.setOnClickListener {
            GlobalScope.launch(Main) {
                val response: Response<ResponseModel> = retrofit.submitWifiList(wifiList)
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "List of Wi-Fi sent", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to send", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkIfAlreadyHavePermission(): Boolean {
        val result = applicationContext.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) }
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestForSpecificPermission() {
        let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE
                ),
                101
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startWifiScanning()
            } else {
                // not granted
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun startWifiScanning(){
        val wifiScanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    scanSuccess()
                } else {
                    scanFailure()
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        applicationContext?.registerReceiver(wifiScanReceiver, intentFilter)

        val success = wifiManager.startScan()
        if (!success) {
            // scan failure handling
            scanFailure()
        }
    }

    private fun scanSuccess() {
        val results = wifiManager.scanResults
        val adapterList = mutableListOf<String>()
        for (res in results) {
            adapterList.add(res.SSID)

            val wifiModel = WifiModel()
            wifiModel.wifiName = res.SSID
            wifiList.add(wifiModel)
        }

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val recyclerAdapter = WifiAdapter(adapterList)
        recyclerView.adapter = recyclerAdapter
        sendButton.visibility = View.VISIBLE
    }

    private fun scanFailure() {
        Toast.makeText(this, "Failed to scan any Wi-Fi nearby", Toast.LENGTH_SHORT).show()
    }
}
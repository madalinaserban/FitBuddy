package com.example.fitbuddyapp.ui.steps_counter

import android.Manifest.permission.ACTIVITY_RECOGNITION
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fitbuddyapp.R
import kotlinx.coroutines.currentCoroutineContext
import java.time.LocalTime
import java.util.*
import java.util.jar.Manifest


class StepsCounterFragment : Fragment(), SensorEventListener {

    private var sensorManager: SensorManager? = null

    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f

    private var ACTIVITY_PERMISSION_RQ = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadData()
        resetSteps()
        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager


        checkForPermission(android.Manifest.permission.ACTIVITY_RECOGNITION, "Activity", ACTIVITY_PERMISSION_RQ)


        val view = inflater.inflate(R.layout.fragment_steps_counter, container, false)
        return view
    }

    private fun checkForPermission(permission: String, name: String, requestCode: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    activity!!.applicationContext,
                    permission
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(
                        activity!!.applicationContext,
                        "$name permission granted!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(
                    permission,
                    name,
                    requestCode
                )

                else -> ActivityCompat.requestPermissions(
                    this.activity!!,
                    arrayOf(permission),
                    requestCode
                )
            }
        }

    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {

        val builder = AlertDialog.Builder(this.activity)

        builder.apply {
            setMessage("Permission to acces your $name is required to use this app!")
            setTitle("Permission required")
            setPositiveButton("Allow") { dialog, which ->
                ActivityCompat.requestPermissions(activity!!, arrayOf(permission), requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                Toast.makeText(
                    activity!!.applicationContext,
                    "$name permission refused",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                Toast.makeText(
                    activity!!.applicationContext,
                    "$name permission granted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        when (requestCode) {
            ACTIVITY_PERMISSION_RQ -> innerCheck("Activity")
        }
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null) {
            Toast.makeText(activity, "No sensor detected on this device", Toast.LENGTH_SHORT)
                .show();
        } else {
            sensorManager?.registerListener(this, stepSensor, 0)

        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            totalSteps = event!!.values[0]
            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()

            view!!.findViewById<TextView>(R.id.tv_stepsTaken).text = ("$currentSteps")
            view!!.findViewById<com.mikhaellopez.circularprogressbar.CircularProgressBar>(R.id.progress_circular)
                .apply {
                    setProgressWithAnimation(currentSteps.toFloat())
                }
        }
    }

    private fun resetSteps() {

//        view!!.findViewById<TextView>(R.id.tv_stepsTaken).setOnClickListener {
//            Toast.makeText(activity, "Long tap to reset steps", Toast.LENGTH_SHORT).show()
//        }

//        view!!.findViewById<TextView>(R.id.tv_stepsTaken).setOnLongClickListener() {
//            previousTotalSteps = totalSteps
//            view!!.findViewById<TextView>(R.id.tv_stepsTaken).text = 0.toString()
//            //saveData()
//
//            true
//        }

//        if(Calendar.getInstance().get(Calendar.HOUR)==0 &&
//            Calendar.getInstance().get(Calendar.MINUTE)==0&&
//            Calendar.getInstance().get(Calendar.SECOND)==0)
        if (  LocalTime.now().hour==19&&LocalTime.now().second==0)
        {
            previousTotalSteps=totalSteps
            totalSteps= 0F
            view!!.findViewById<TextView>(R.id.tv_stepsTaken).text=0.toString()
           saveData()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun saveData() {
        val sharedPreferences = activity!!.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = activity!!.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1", 0f)
        previousTotalSteps = savedNumber
    }

}
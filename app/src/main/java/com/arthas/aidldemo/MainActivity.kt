package com.arthas.aidldemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.arthas.aidldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    private val mBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater); }
    lateinit var myBinder: ITestAidlInterface
    private val serviceConnection: ServiceConnection by lazy {
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                myBinder = ITestAidlInterface.Stub.asInterface(service)
                Log.e(TAG, System.currentTimeMillis().toString())
                Person("张三", 10).let {
                    myBinder.setOnewayPerson(it)
                    Log.e(TAG, System.currentTimeMillis().toString())
                }
                Person("张三", 10).let {
                    myBinder.setPerson(it)
                    Log.e(TAG, System.currentTimeMillis().toString())
                }

            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.e(TAG, "onServiceDisconnected")
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        Log.e(TAG, android.os.Process.myPid().toString())
        mBinding.btnStartService.setOnClickListener {
            val intent = Intent(this, RemoteService::class.java)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }


}
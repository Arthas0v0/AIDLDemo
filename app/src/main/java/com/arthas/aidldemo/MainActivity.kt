package com.arthas.aidldemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.arthas.aidldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    private val mBinding :ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater); }
    lateinit var myBinder: ITestAidlInterface
    private val serviceConnection : ServiceConnection by lazy {
        object :ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
               myBinder =  ITestAidlInterface.Stub.asInterface(service)
                Log.e(TAG, myBinder.person.toString())
                myBinder.person = Person("李四",10)
                Log.e(TAG, myBinder.person.toString())
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.e(TAG,"onServiceDisconnected")
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        Log.e(TAG, android.os.Process.myPid().toString())
        mBinding.btnStartService.setOnClickListener {
            val intent = Intent(this,RemoteService::class.java)
            bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }


}
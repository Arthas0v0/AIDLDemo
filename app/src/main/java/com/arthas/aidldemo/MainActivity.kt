package com.arthas.aidldemo

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.arthas.aidldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    private val mBinding :ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater); }
    lateinit var messenger: Messenger
    val clientMessenger:Messenger by lazy { Messenger(@SuppressLint("HandlerLeak")
    object :Handler(){
        override fun handleMessage(msg: Message) {
           if (msg.what == SERVICE){
               Log.e(TAG,"from service")
           }
            super.handleMessage(msg)
        }
    })
    }
    private val serviceConnection : ServiceConnection by lazy {
        object :ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                messenger = Messenger(service)
                messenger.send(Message.obtain().apply {
                    what = CLIENT
                    replyTo = clientMessenger
                })
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
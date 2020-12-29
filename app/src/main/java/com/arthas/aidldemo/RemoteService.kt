package com.arthas.aidldemo

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log

val TAG = RemoteService::class.java.simpleName
const val CLIENT = 0
const val SERVICE = 1
class RemoteService : Service() {

    override fun onBind(intent: Intent): IBinder {
        Log.e(TAG, android.os.Process.myPid().toString())
        val messenger  = Messenger(@SuppressLint("HandlerLeak")
        object : Handler(){
            override fun handleMessage(msg: Message) {
                if (msg.what == CLIENT){
                    Log.e(TAG,"from client")
                    val reply = msg.replyTo
                    reply.send(Message().apply {
                        what = SERVICE
                    })
                }
                super.handleMessage(msg)
            }
        })
        return messenger.binder
    }

}
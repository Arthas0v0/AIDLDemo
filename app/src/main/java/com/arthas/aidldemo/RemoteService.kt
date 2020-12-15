package com.arthas.aidldemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
val TAG = RemoteService::class.java.simpleName
class RemoteService : Service() {

    override fun onBind(intent: Intent): IBinder {
        Log.e(TAG, android.os.Process.myPid().toString())
        return MyBinder().asBinder()
    }

    class MyBinder : ITestAidlInterface.Stub() {
        var userName = "张三"
        override fun getName(): String {
            return userName
        }

        override fun setName(name: String) {
            Log.e(TAG,name)
            userName = name
        }

    }
}
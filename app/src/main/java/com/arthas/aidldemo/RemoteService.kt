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
        private var person:Person? = Person("张三",18)
        override fun setPerson(person: Person?) {
            this.person = person
            Thread.sleep(2000)
        }

        override fun setOnewayPerson(person: Person?) {
            this.person = person
            Thread.sleep(2000)
        }

        /*
        客户端测试版本
        override fun setInPerson(person: Person?) {
           this.person = person
        }
        override fun setOutPerson(person: Person?) {
            this.person = person
        }
        override fun setInOutPerson(person: Person?) {
            this.person = person
        }
*/
        override fun getPerson(): Person? {
            return person
        }


    }
}
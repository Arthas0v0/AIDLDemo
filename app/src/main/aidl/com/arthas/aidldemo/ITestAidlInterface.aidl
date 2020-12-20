// ITestAidlInterface.aidl
package com.arthas.aidldemo;

// Declare any non-default types here with import statements
import com.arthas.aidldemo.Person;
interface ITestAidlInterface {
    void setPerson(in Person person);
   oneway void setOnewayPerson(in Person person);

    Person getPerson();
}
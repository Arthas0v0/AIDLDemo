// ITestAidlInterface.aidl
package com.arthas.aidldemo;

// Declare any non-default types here with import statements
import com.arthas.aidldemo.Person;
interface ITestAidlInterface {
    void setInPerson(in Person person);
    void setOutPerson(out Person person);
    void setInOutPerson(inout Person person);
    Person getPerson();
}
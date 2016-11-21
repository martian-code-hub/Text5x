// PersonManager.aidl
package com.example.martian;

// Declare any non-default types here with import statements
import com.example.martian.bean.Person;

interface PersonManager {

  List<Person> getPersons();

  void addPerson(inout Person person);

  void updatePerson(inout Person person,int index);

   boolean disConnected();
}

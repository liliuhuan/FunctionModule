// ISalary.aidl
package com.liliuhuan.com.simplyskill;

// Declare any non-default types here with import statements
 import com.liliuhuan.com.simplyskill.entity.Person;
 import com.liliuhuan.com.simplyskill.entity.Salary;

interface ISalary {
        Person get(int num);
        Salary getPerson(in Person ower);
}

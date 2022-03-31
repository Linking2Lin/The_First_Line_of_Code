package com.example.servicetest.kotlins.changes.inverse

import com.example.servicetest.kotlins.changes.Person
import com.example.servicetest.kotlins.changes.Student

fun main() {
    val trans = object : Transformer<Person>{
        override fun transform(t: Person): String {
            return "${t.name}  ${t.age}"
        }
    }
    handleTransformer(trans)
}

fun handleTransformer(trans:Transformer<Student>){
    val student = Student("Tom",777)
    val result = trans.transform(student)
}
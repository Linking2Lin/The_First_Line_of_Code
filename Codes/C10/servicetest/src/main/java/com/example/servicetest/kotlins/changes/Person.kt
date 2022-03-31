package com.example.servicetest.kotlins.changes

open class Person(val name:String,val age:Int)

class Student(name: String,age: Int): Person(name, age)
class Teacher(name: String,age: Int): Person(name, age)
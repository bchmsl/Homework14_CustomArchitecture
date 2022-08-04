package com.bchmsl.homework14_customarchitecture

import androidx.appcompat.app.AppCompatActivity

abstract class AppCompatPage<T: AppCompatActivity> {
    abstract fun openPage(activity: AppCompatActivity)
}
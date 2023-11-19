package com.gabiley.xisaabso.MyViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MyViewModel: ViewModel() {
    var Total    by mutableStateOf("")
    var USDCurr  by mutableStateOf("")
    var SLSHCurr by mutableStateOf("")

}
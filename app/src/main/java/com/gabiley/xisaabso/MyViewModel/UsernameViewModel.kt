package com.gabiley.xisaabso.MyViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gabiley.diwaankagabiley.SessionClasses.Sessions


// commented this function at 21 mar 2023

class UsernameViewModel: ViewModel() {
   var person by mutableStateOf<Sessions?>(null)
      private set

   fun addPerson(newPerson: Sessions) {
      person = newPerson
   }

}

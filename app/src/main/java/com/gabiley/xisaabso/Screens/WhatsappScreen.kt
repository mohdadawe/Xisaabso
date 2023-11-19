package com.gabiley.xisaabso.Screens

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gabiley.xisaabso.Classes.Screen
import com.gabiley.xisaabso.Notification.Notfication
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WhatsappScreen_WithTopBar(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Whatsapp") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Home.route){
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                //backgroundColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.primary,
                elevation = 0.dp
            )
        },
        content = { WhatsappScreen(navController) }
    )
}



@Composable
fun WhatsappScreen(navController: NavController) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {

        val box = remember { mutableStateOf(false) }

        if(box.value) {
            AlertDialog(
                onDismissRequest = {  },
                title = {
                    Text("Here is a description text of the dialog")
                },
                text = {
                    Text("Here is a description text of the dialog")
                },
                dismissButton = {
                    Button(
                        onClick = {
                            box.value = false
                        }
                    ) {
                        Text(text = "cancel", fontSize = 22.sp)
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            box.value = false

                            val queue = Volley.newRequestQueue(context)
                            val url = "https://xisaabso.online/sendwhatsapp"

                            // Request a string response from the provided URL.
                            val sr: StringRequest = @RequiresApi(Build.VERSION_CODES.O)
                            object : StringRequest(
                                Method.GET, url,
                                Response.Listener {
                                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                                },
                                Response.ErrorListener {
                                    //your error
                                }) {
                                //override fun getParams(): Map<String, String> {
                                //val params: MutableMap<String, String> = HashMap()

                                //params["SessionID"] = sessionID.value.toString()
                                //params["CustomerID"] = customerID.value.toString()
                                //params["Slsh"]       = Money.value.toString()
                                //return params

                                //}
                                //@Throws(AuthFailureError::class)
                                //override fun getHeaders(): Map<String, String> {
                                //val params: MutableMap<String, String> = HashMap()
                                //params["Content-Type"] = "application/x-www-form-urlencoded"
                                //return params
                                //}
                            }

                            // Add the request to the RequestQueue.
                            queue.add(sr)


                        }
                    ) {
                        Text(text = "Submit", fontSize = 22.sp)
                    }
                },
            )
        }


        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                box.value = true
            }
        ) {
            Text(text = "Button", fontSize = 22.sp)
        }
        
        Spacer(modifier = Modifier.height(20.dp))

    }
    
}
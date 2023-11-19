package com.gabiley.xisaabso.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gabiley.xisaabso.Classes.Screen
import com.gabiley.xisaabso.DataStore.StoreUserName
import kotlinx.coroutines.launch

@Composable
fun VerificationScreen(navController: NavController, myName: String?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {

        val context = LocalContext.current
        var verification by remember { mutableStateOf("") }
        val maxLength = 10
        //val SharedSession = loginViewModel.person

        val scope = rememberCoroutineScope()

        // we instantiate the saveEmail class
        val person = StoreUserName(context)


        ////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////

        //val Username_Saved = person.getUsername.collectAsState(initial = "")
        //val AccountStatus  = person.getAccountStatus.collectAsState(initial = "")
        //val get = person.getVerificationScreen_Status.collectAsState(initial = "")


        Text(text = "Password Screen", fontSize = 33.sp)

        Spacer(modifier = Modifier.height(20.dp))


        Text(text = myName.toString(), fontSize = 15.sp)


        //Spacer(modifier = Modifier.height(20.dp))

        val pattern = remember { Regex("[0-9]*") }


        OutlinedTextField(
            textStyle = TextStyle(fontSize = 24.sp),
            value = verification,
            onValueChange = {
                if (it.length <= maxLength) {
                    if (it.matches(pattern)) {
                        verification = it
                    }
                }
            },
            label = { Text("Enter Your password") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        //Spacer(modifier = Modifier.height(20.dp))

        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////


        Button(onClick = {
            if (verification.isNotBlank()) {
                val queue = Volley.newRequestQueue(context)
                val url = "https://xisaabso.online/ver"

                val sr: StringRequest = object : StringRequest(
                    Method.POST, url,
                    Response.Listener { response ->
                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()

                        if(response == "Successful Login") {
                            scope.launch {
                                person.saveVerficationCode("true")
                                navController.navigate(Screen.Home.route)

                                Toast.makeText(
                                    context,
                                    "waad ku guulaysatay",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            /*
                            val queue2 = Volley.newRequestQueue(context)
                            val url2 = "https://xisaabso.online/unblocked"

                            val sr: StringRequest = object : StringRequest(
                                Method.POST, url2,
                                Response.Listener { response ->
                                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show()

                                    if(response == "sax") {
                                        scope.launch {
                                            person.saveVerificationScreen_Status("OFF")
                                            //person.savePassword("true")
                                            //navController.navigate(Screen.Home.route)

                                        }
                                        navController.navigate(Screen.PasswordScreen.route)
                                    }


                                },
                                Response.ErrorListener {
                                    //your error
                                }) {
                                override fun getParams(): Map<String, String> {
                                    val params: MutableMap<String, String> = HashMap()
                                    params["UsernameSession"] = myName.toString()
                                    return params
                                }

                                //@Throws(AuthFailureError::class)
                                //override fun getHeaders(): Map<String, String> {
                                //val params: MutableMap<String, String> = HashMap()
                                //params["Content-Type"] = "application/x-www-form-urlencoded"
                                //return params
                                //}

                            }
                            queue2.add(sr)
                            */


                        }
                        else {
                            Toast.makeText(
                                context,
                                "waa qalad code-ku...",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    Response.ErrorListener {
                        //your error
                    }) {
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap()

                        val delim = " "
                        val list = myName?.split(delim)

                        params["Username"]   = list?.get(1).toString()
                        params["VerifyCode"] = verification

                        return params
                    }

                    //@Throws(AuthFailureError::class)
                    //override fun getHeaders(): Map<String, String> {
                    //val params: MutableMap<String, String> = HashMap()
                    //params["Content-Type"] = "application/x-www-form-urlencoded"
                    //return params
                    //}

                }
                queue.add(sr)

            } else {
                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
            }

        }) {
            Text(text = "Soo Saar", fontSize = 23.sp)
        }

        Spacer(modifier = Modifier.height(60.dp))


    }
}

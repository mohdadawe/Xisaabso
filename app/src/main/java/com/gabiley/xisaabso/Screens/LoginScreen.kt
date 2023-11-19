package com.gabiley.xisaabso.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
fun LoginScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        val username = rememberSaveable { mutableStateOf("") }

        val maxLength = 9
        //val SharedSession = loginViewModel.person

        val scope = rememberCoroutineScope()
        // we instantiate the saveEmail class
        val person = StoreUserName(context)
        val Result_Username = person.getUserName.collectAsState(initial = "")

        /*
        if (Result_Username.value?.isEmpty() == true) {
            Text(
                text = "Empty",
                fontSize = 33.sp
            )
        }
        else if (Result_Username.value?.isEmpty() == false) {
            Text(
                text = Result_Username.value.toString(),
                fontSize = 33.sp
            )
        }
        */

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Login Page", fontSize = 33.sp)

        Spacer(modifier = Modifier.height(20.dp))

        val pattern = remember { Regex("[0-9]*") }

        OutlinedTextField(
            textStyle = TextStyle(fontSize = 24.sp),
            value = username.value,
            onValueChange = {
                if (it.length <= maxLength) {
                    if (it.matches(pattern)) {
                        username.value = it
                    }
                }
            },
            label = { Text("Enter Your username") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////

        //CircularProgressIndicator()


        Button(onClick = {
            if (username.value.isNotBlank()) {
                val queue = Volley.newRequestQueue(context)
                val url = "https://xisaabso.online/UsernameLogin"

                val sr: StringRequest = object : StringRequest(
                    Method.POST, url,
                    Response.Listener { response ->
                        //Toast.makeText(context, response, Toast.LENGTH_SHORT).show()

                        if(response == "no") {
                            Toast.makeText(
                                context,
                                "Numberkani ma diiwan gashanaa...",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        else {
                            scope.launch {
                                Toast.makeText(
                                    context,
                                    "masha allah",
                                    Toast.LENGTH_LONG
                                ).show()


                                navController.navigate(
                                    Screen.ConvertUsername_Info.route + "/${response}"
                                )

                                /*
                                person.saveVerficationCode("true")
                                navController.navigate(Screen.Home.route)

                                Toast.makeText(
                                    context,
                                    "waad ku guulaysatay",
                                    Toast.LENGTH_LONG
                                ).show()
                                */

                            }
                        }

                    },
                    Response.ErrorListener {
                        //your error
                    }) {
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap()
                        params["Username"] = username.value
                        //params["VerifyCode"]      = verification
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
            Text(text = "Login", fontSize = 23.sp)
        }







        /*
        Button(
            onClick = {
                if (username.value.isNotBlank()) {
                    val url = "https://xisaabso.online/UsernameLogin"

                    val queue = Volley.newRequestQueue(context)
                    val jsonObjectRequest = object : JsonObjectRequest(
                        Method.POST, url, null,
                        Response.Listener { response ->
                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()


                            val myDataResult = response.getString("status")

                            if(myDataResult == "ok") {
                                val getDataArray   = response.getJSONArray("data")
                                val getDataObject  = getDataArray.getJSONObject(0)
                                val iD: String     = getDataObject.getString("ID")
                                val userID: String = getDataObject.getString("UserID")


                                scope.launch {
                                    //val delim = " "
                                    //val list = response.split(delim)
                                    // commented below code in 14 nov 2023
                                    //person.saveUserName(iD)


                                    navController.navigate(
                                        Screen.ChoosingAccountFromUser.route + "/${getDataArray}"
                                    )


                                    Toast.makeText(
                                        context,
                                        response.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()


                                    // commented below code in 14 nov 2023
                                    navController.navigate(
                                        Screen.Verification_Screen.route + "/${iD}"
                                    )

                                }
                                //Log.d("Login", "ID: $iD - UserID: $userID")
                            }

                            else if(myDataResult == "no") {
                                Log.d("Login", myDataResult.toString())
                                Toast.makeText(
                                    context,
                                    "Numberkani ma diwaan gashanaa...",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        },
                        Response.ErrorListener {
                            // Do something when error occurred
                        }
                    ) {
                        override fun getBody(): ByteArray {
                            val parameters = HashMap<String, String>()
                            parameters["Username"] = username.value
                            return JSONObject(parameters.toString()).toString().toByteArray()
                        }
                    }
                    queue.add(jsonObjectRequest)
                }
                else {
                    Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Login", fontSize = 23.sp)
        }

        */




    }
}
package com.gabiley.xisaabso.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
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
import com.gabiley.xisaabso.DataStore.StoreUserName


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChooseUsernameForLogin_WithTopBar(
    ID: String?,
    UserID: String?,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "All Vouchers By Month") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            /*
                            navController.navigate(Screen.Home.route){
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                }
                            }
                            */
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
        content = { ChooseUsernameForLogin(ID, UserID, navController) }
    )
}


// on below line we are creating
// a pop up window dialog method
@Composable
fun ChooseUsernameForLogin(
    ID: String?,
    UserID: String?,
    navController: NavController
) {
    val ctx = LocalContext.current
    // on below line we are creating variables.
    val Total_Iibka_SLSH              = remember { mutableStateOf("") }
    val Total_Dhigasho_USD            = remember { mutableStateOf("") }
    val Total_Dhigasho_SLSH           = remember { mutableStateOf("") }
    val Total_Sarif_SLSH              = remember { mutableStateOf("") }
    val Total_Dhigasho_iyo_Sarif_SLSH = remember { mutableStateOf("") }
    val Total_Sarif_Faaido            = remember { mutableStateOf("") }
    val Total_Sarif_SLSH_By_USD       = remember { mutableStateOf("") }
    val Total_Kalabixid_USD           = remember { mutableStateOf("") }
    val Total_Kalabixid_SLSH          = remember { mutableStateOf("") }
    val Baaqi_USD                     = remember { mutableStateOf("") }
    val Baaqi_SLSH                    = remember { mutableStateOf("") }
    val progress                      = remember { mutableStateOf(true) }

    //val maxLength = 9
    //val SharedSession = loginViewModel.person

    //val scope = rememberCoroutineScope()

    // we instantiate the saveEmail class
    val person = StoreUserName(ctx)

    val Result_Username = person.getUserName.collectAsState(initial = "")
    //val Result_Expiry   = person.getExpiryAccount.collectAsState(initial = "")


    jsonParsing(
        ctx,
        Result_Username,
    )

    // on the below line we are creating a column.
    Column(
        // in this column we are specifying
        // modifier to add padding and fill
        // max size
        modifier = Modifier
            .fillMaxSize(),
        //.verticalScroll(rememberScrollState()),
        //.padding(horizontal = 20.dp),
        // on below line we are adding horizontal alignment
        // and vertical arrangement
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        // we instantiate the saveEmail class
        val person = StoreUserName(context)
        val resultUsername = person.getUserName.collectAsState(initial = "")
        
        Text(text = resultUsername.value.toString(), fontSize = 33.sp)

        Spacer(modifier = Modifier.height(20.dp))



        Button(onClick = {
            navController.navigate(Screen.Verification_Screen.route)
        }) {
            Text(text = "Go", fontSize = 22.sp)
        }



        /*
        // on below line we are creating a column.
        if (progress.value) {
            Column(
                // in this column we are specifying
                // modifier to add padding and fill
                // max size
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),

                // on below line we are adding horizontal alignment
                // and vertical arrangement
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // below line is use to display a circular progress bar.
                CircularProgressIndicator(
                    // below line is use to add padding
                    // to our progress bar.
                    modifier = Modifier.padding(16.dp),
                    // below line is use to add color to our progress bar.
                    color = colorResource(id = R.color.purple_200),
                    // below line is use to add stroke
                    // width to our progress bar.
                    strokeWidth = Dp(value = 4F)
                )
            }
        }
        */

        /*
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxSize(),
            //.background(Color.LightGray),
            contentPadding = PaddingValues(
                //horizontal = 10.dp,
                //vertical = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item() {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {},
                    //.height(150.dp),
                    //.background(Color.Green)
                    RoundedCornerShape(0.dp),
                    elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            //.background(Color.Green)
                            .padding(
                                top = 30.dp,
                                bottom = 30.dp,
                                start = 20.dp,
                                end = 20.dp
                            )
                    ) {

                        Text(text = "Baaqi", fontSize = 27.sp)

                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                //.background(Color.Red)
                                .fillMaxWidth()
                            //.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                //.fillMaxSize()
                                //.background(Color.Yellow)

                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "SLSH",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = Baaqi_SLSH.value,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                //.fillMaxSize()
                                //.background(Color.Yellow)

                            ) {

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "USD",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "$" + Baaqi_USD.value,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }

            item() {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        },
                    //.height(150.dp),
                    //.background(Color.Green)
                    RoundedCornerShape(0.dp),
                    elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            //.background(Color.Green)
                            .padding(
                                top = 30.dp,
                                bottom = 30.dp,
                                start = 20.dp,
                                end = 20.dp
                            )
                    ) {

                        Text(text = "Ka Jarid", fontSize = 27.sp)

                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                //.background(Color.Red)
                                .fillMaxWidth()
                            //.weight(1f)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                //.fillMaxSize()
                                //.background(Color.Yellow)

                            ) {

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "SLSH",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = Total_Iibka_SLSH.value,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                //.fillMaxSize()
                                //.background(Color.Yellow)

                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "USD",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "$0" ,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }

            item() {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        },
                    //.height(150.dp),
                    //.background(Color.Green)
                    RoundedCornerShape(0.dp),
                    elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            //.background(Color.Green)
                            .padding(
                                top = 30.dp,
                                bottom = 30.dp,
                                start = 20.dp,
                                end = 20.dp
                            )
                    ) {

                        Text(text = "Dhigasho", fontSize = 27.sp)

                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                //.background(Color.Red)
                                .fillMaxWidth()
                            //.weight(1f)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                //.fillMaxSize()
                                //.background(Color.Yellow)

                            ) {

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "SLSH",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = Total_Dhigasho_SLSH.value,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                //.fillMaxSize()
                                //.background(Color.Yellow)

                            ) {

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "USD",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "$" + Total_Dhigasho_USD.value,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }

            item() {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        },
                    //.height(150.dp),
                    //.background(Color.Green)
                    RoundedCornerShape(0.dp),
                    elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            //.background(Color.Green)
                            .padding(
                                top = 30.dp,
                                bottom = 30.dp,
                                start = 20.dp,
                                end = 20.dp
                            )
                    ) {

                        Text(text = "Sarrif", fontSize = 27.sp)

                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                //.background(Color.Red)
                                .fillMaxWidth()
                            //.weight(1f)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                //.fillMaxSize()
                                //.background(Color.Yellow)

                            ) {

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "USD",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "$" + Total_Sarif_SLSH_By_USD.value,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    //fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
        */
    }
}


fun jsonParsing(
    ctx: Context,
    Result_Username: State<String?>,
) {
    //val url =

    val queue = Volley.newRequestQueue(ctx)
    val sr: StringRequest = object : StringRequest(
        Method.POST, "https://xisaabso.online/getallusernamesbyuser",
        Response.Listener { response ->
            //Toast.makeText(ctx, response.toString(), Toast.LENGTH_LONG).show()

            Log.d("welcome", response)


            if (response.toString() == "This username is not exist") {
                Toast.makeText(ctx, "Ma Jiro Qofkani", Toast.LENGTH_LONG).show()
            }
            else if (response.toString() == "Your Account is Expired") {
                Toast.makeText(ctx, "EXPIRED ACCOUNT", Toast.LENGTH_LONG).show()
            }
            else {
                /*
                scope.launch {
                    val delim = " "
                    val list = response.split(delim)
                    Toast.makeText(context, list[0].toString(), Toast.LENGTH_SHORT).show()
                    person.saveUserName(list[0])
                }
                //navController.navigate(Screen.Home.route)
                //navController.navigate(Screen.ChooseUsernameForLogin.route)


                navController.navigate(
                    //Screen.CustomerDetails_Screen.route + "/${data.CustomerID}"
                    Screen.ChooseUsernameForLogin.route + "/${Result_Username.value}"
                )
                */
            }
        },
        Response.ErrorListener {
            //your error
        }) {
        override fun getParams(): Map<String, String> {
            val params: MutableMap<String, String> = HashMap()
            params["Username"] = Result_Username.value.toString()
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




}


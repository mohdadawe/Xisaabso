package com.gabiley.xisaabso.Screens.Xis_Vouchers

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.items
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.gabiley.xisaabso.Classes.Screen
import com.gabiley.xisaabso.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Dashboard_WithTopBar(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "All Vouchers By Month") },
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
        content = { Dashboard() }
    )
}


// on below line we are creating
// a pop up window dialog method
@Composable
fun Dashboard() {
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


    jsonParsing(
        ctx,
        Total_Iibka_SLSH,
        Total_Dhigasho_USD,
        Total_Dhigasho_SLSH,
        Total_Sarif_SLSH,
        Total_Dhigasho_iyo_Sarif_SLSH,
        Total_Sarif_Faaido,
        Total_Sarif_SLSH_By_USD,
        Total_Kalabixid_USD,
        Total_Kalabixid_SLSH,
        Baaqi_USD,
        Baaqi_SLSH,
        progress
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
    }
}


fun jsonParsing(
    ctx: Context,
    Total_Iibka_SLSH: MutableState<String>,
    Total_Dhigasho_USD: MutableState<String>,
    Total_Dhigasho_SLSH: MutableState<String>,
    Total_Sarif_SLSH: MutableState<String>,
    Total_Dhigasho_iyo_Sarif_SLSH: MutableState<String>,
    Total_Sarif_Faaido: MutableState<String>,
    Total_Sarif_SLSH_By_USD: MutableState<String>,
    Total_Kalabixid_USD: MutableState<String>,
    Total_Kalabixid_SLSH: MutableState<String>,
    Baaqi_USD: MutableState<String>,
    Baaqi_SLSH: MutableState<String>,
    progress: MutableState<Boolean>,
) {

    val url = "https://xisaabso.online/dashboard"

    val queue = Volley.newRequestQueue(ctx)
    val jsonArrayRequest = JsonArrayRequest(
        Request.Method.GET, url, null,
        { response ->
            try {
                // on below line we are getting data from our response
                // and setting it in variables.
                val ddd = response.getJSONObject(0)

                val Total_Iibka_SLSH_: String              = ddd.getString("Total_Iibka_SLSH")
                val Total_Dhigasho_USD_: String            = ddd.getString("Total_Dhigasho_USD")
                val Total_Dhigasho_SLSH_: String           = ddd.getString("Total_Dhigasho_SLSH")
                val Total_Sarif_SLSH_: String              = ddd.getString("Total_Sarif_SLSH")
                val Total_Dhigasho_iyo_Sarif_SLSH_: String = ddd.getString("Total_Dhigasho_iyo_Sarif_SLSH")
                val Total_Sarif_Faaido_: String            = ddd.getString("Total_Sarif_Faaido")
                val Total_Sarif_SLSH_By_USD_: String       = ddd.getString("Total_Sarif_SLSH_By_USD")
                val Total_Kalabixid_USD_: String           = ddd.getString("Total_Kalabixid_USD")
                val Total_Kalabixid_SLSH_: String          = ddd.getString("Total_Kalabixid_SLSH")
                val Baaqi_USD_: String                     = ddd.getString("Baaqi_USD")
                val Baaqi_SLSH_: String                    = ddd.getString("Baaqi_SLSH")


                // on below line we are setting
                // data to our variables which we have passed.
                Total_Iibka_SLSH.value              = Total_Iibka_SLSH_
                Total_Dhigasho_USD.value            = Total_Dhigasho_USD_
                Total_Dhigasho_SLSH.value           = Total_Dhigasho_SLSH_
                Total_Sarif_SLSH.value              = Total_Sarif_SLSH_
                Total_Dhigasho_iyo_Sarif_SLSH.value = Total_Dhigasho_iyo_Sarif_SLSH_
                Total_Sarif_Faaido.value            = Total_Sarif_Faaido_
                Total_Sarif_SLSH_By_USD.value       = Total_Sarif_SLSH_By_USD_
                Total_Kalabixid_USD.value           = Total_Kalabixid_USD_
                Total_Kalabixid_SLSH.value          = Total_Kalabixid_SLSH_
                Baaqi_USD.value                     = Baaqi_USD_
                Baaqi_SLSH.value                    = Baaqi_SLSH_
                progress.value                      = false

            } catch (e: Exception) {
                // on below line we are
                // handling our exception.
                e.printStackTrace()
            }
            // TODO do something with the variables here
        }, { error ->
            Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show()
        }
    )
    queue.add(jsonArrayRequest)

}



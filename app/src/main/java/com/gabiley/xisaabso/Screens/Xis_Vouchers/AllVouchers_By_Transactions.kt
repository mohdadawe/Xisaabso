package com.gabiley.xisaabso.Screens.Xis_Vouchers

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

import com.gabiley.xisaabso.Classes.Screen
import com.gabiley.xisaabso.Classes.Xisaabso.XisaabsoData
import com.gabiley.xisaabso.Paging.XisaabsoAllVouchersByTransactions.Xisaabso_All_Vouchers_By_TransactionsViewModel
import com.gabiley.xisaabso.connection.ConnectionState
import com.gabiley.xisaabso.connection.connectivityState
//import androidx.paging.compose.items
//import com.gabiley.diwaankagabiley.Classes.Screen
//import com.gabiley.xisaabso.Classes.Xisaabso.XisaabsoData
//import com.gabiley.xisaabso.DataStore.StoreUserName
//import com.gabiley.xisaabso.Paging.XisaabsoAllVouchersByTransactions.Xisaabso_All_Vouchers_By_TransactionsViewModel
//import com.gabiley.diwaankagabiley.RefreshViewModel.MyViewModel
//import com.gabiley.xisaabso.connection.ConnectionState
//import com.gabiley.xisaabso.connection.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun Xisaabso_All_Vouchers_By_Transactions_WithTopBar(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "All Vouchers By Month")
                },
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
        content = {
            ConnectivityStatus_Xisaabso_All_Vouchers_By_Transactions(
                navController,
                /*viewModel*/
            )
        }
    )
}


@ExperimentalCoroutinesApi
@Composable
fun ConnectivityStatus_Xisaabso_All_Vouchers_By_Transactions(
    navController: NavController
) {
    // This will cause re-composition on every network state change
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    if (isConnected) {
        Xisaabso_All_Vouchers_By_Transactions(navController)
    }
    else {
        // Show UI for No Internet Connectivity
        //Toast.makeText(context, "Not connected", Toast.LENGTH_SHORT).show()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Not Connected")
        }
    }
}


@SuppressLint("MutableCollectionMutableState")
@Composable
fun Xisaabso_All_Vouchers_By_Transactions(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        //.background(MainScreenColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        //SearchBar(onSearch = )

        val context = LocalContext.current
        //Toast.makeText(context, "welcome", Toast.LENGTH_SHORT).show()
        //val pagingData = viewModel.pagingData.collectAsLazyPagingItems()

        //val viewModel: MyViewModel = viewModel()
        //val isRefreshing by viewModel.isRefreshing.collectAsState()

        val view_Model = hiltViewModel<Xisaabso_All_Vouchers_By_TransactionsViewModel>()
        val pagingData = view_Model.getDDDDD().collectAsLazyPagingItems()

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 12.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            if(pagingData.loadState.refresh is LoadState.NotLoading) {
                items(pagingData){
                    Cards_Xisaabso_All_Vouchers_By_Transactions(
                        data = it!!,
                        navController
                    )
                }
            }

            if(pagingData.loadState.refresh is LoadState.Loading) {
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            if(pagingData.loadState.refresh is LoadState.Error) {
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        Text(text = "Error....", modifier = Modifier.clickable {
                            pagingData.refresh()
                        })
                    }
                }
            }

            if(pagingData.loadState.append is LoadState.Loading) {
                item {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            if(pagingData.loadState.append is LoadState.Error) {
                item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(text = "Error....", modifier = Modifier.clickable {
                            pagingData.refresh()
                        })
                    }
                }
            }

            if(pagingData.loadState.prepend is LoadState.Loading) {
                item {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            if(pagingData.loadState.prepend is LoadState.Error) {
                item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(text = "Error....", modifier = Modifier.clickable {
                            pagingData.refresh()
                        })
                    }
                }
            }

        }

    } // END Column
}


@Composable
fun Cards_Xisaabso_All_Vouchers_By_Transactions(
    data: XisaabsoData,
    navController: NavController
) {
    val context = LocalContext.current
    //val REQUEST_PHONE_CALL = 1
    val scope  = rememberCoroutineScope()
    //val person = StoreUserName(context)

    Card(
        modifier = Modifier
            .padding(
                start = 0.dp,
                end = 0.dp,
                top = 0.dp,
                bottom = 10.dp
            )
            //.fillMaxWidth()
            .fillMaxSize(),
        RoundedCornerShape(0.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                //.background(Color.Green)
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                //.background(Color.LightGray)
            ) {
                Text(
                    text = "#" + data.ID.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = data.TimeAgo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.padding(7.dp))

            if (data.Dhigasho_USD !== null) {
                Text(
                    text = "${data.FirstName} ${data.SecondName} ${data.ThirdName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Waxaad u Dhigtay: $${data.Dhigasho_USD}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            else if (data.Sarif_SLSH !== null) {
                Text(
                    text = "${data.FirstName} ${data.SecondName} ${data.ThirdName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Waxaad Sariftay: $" + data.Sarif_SLSH_By_USD.toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

            }
            else if (data.Dhigasho_SLSH !== null) {
                Text(
                    text = "${data.FirstName} ${data.SecondName} ${data.ThirdName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Waxaad Ku Shubtay: " + data.Dhigasho_SLSH.toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            else if (data.Iibka_SLSH !== null) {
                Text(
                    text = "${data.FirstName} ${data.SecondName} ${data.ThirdName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Waxa Lagaa Jaray: " + data.Iibka_SLSH.toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.padding(7.dp))


            ///////////////////////////////////////////////////
            ///////////////////////////////////////////////////
            ///////////////////////////////////////////////////


            if (data.Dhigasho_USD !== null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        //.background(color = Color.Yellow)
                        .fillMaxSize()
                        .padding(
                            start = 30.dp,
                            end = 30.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        )
                ) {
                    // DOLLAR
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.Gray)
                    ) {
                        Text(
                            text = "USD",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "$" + data.Baaqi_USD_Live,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }

                    // SLSH
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.LightGray)
                    ) {
                        Text(
                            text = "B. SLSH",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "" + data.Baaqi_SLSH_Live,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }

            else if (data.Sarif_SLSH !== null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        //.background(color = Color.Yellow)
                        .fillMaxSize()
                        .padding(
                            start = 30.dp,
                            end = 30.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        )
                ) {
                    // TOTAL USD
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.LightGray)
                    ) {
                        Text(
                            text = "B. USD",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$" + data.Baaqi_USD_Live,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    // TOTAL SLSH
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.LightGray)
                    ) {
                        Text(
                            text = "B. SLSH",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "" + data.Baaqi_SLSH_Live,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            else if (data.Dhigasho_SLSH !== null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        //.background(color = Color.Yellow)
                        .fillMaxSize()
                        .padding(
                            start = 30.dp,
                            end = 30.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.Gray)
                    ) {
                        Text(
                            text = "Dhigasho SLSH",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "" + data.Dhigasho_SLSH,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    // TOTAL
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.LightGray)
                    ) {
                        Text(
                            text = "B. SLSH",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "" + data.Baaqi_SLSH_Live,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // SHILIN

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.LightGray)
                    ) {
                        Text(
                            text = "SLSH",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )


                        if (data.Sarif_SLSH !== null) {
                            Text(
                                text = data.Sarif_SLSH.toString() /* + " ($${data.Sarif_SLSH_By_USD})"*/,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        else {
                            Text(
                                text = "0",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            else if (data.Iibka_SLSH !== null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        //.background(color = Color.Yellow)
                        .fillMaxSize()
                        .padding(
                            start = 30.dp,
                            end = 30.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        )
                ) {
                    // TOTAL USD
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.LightGray)
                    ) {
                        Text(
                            text = "B. USD",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$" + data.Baaqi_USD_Live,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // TOTAL SLSH
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        //.background(Color.LightGray)
                    ) {
                        Text(
                            text = "B. SLSH",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "" + data.Baaqi_SLSH_Live,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    } //END Card
}



package com.gabiley.xisaabso.Screens.Xis_Vouchers

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.paging.compose.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gabiley.xisaabso.Classes.Customer_data.Data
import com.gabiley.xisaabso.Classes.Screen
import com.gabiley.xisaabso.DataStore.StoreUserName
import com.gabiley.xisaabso.Notification.Notfication
import com.gabiley.xisaabso.Paging.Customer.Xisaabso_CustomerDataViewModel
import com.gabiley.xisaabso.Screens.Search.priceFilter
import com.gabiley.xisaabso.connection.ConnectionState
import com.gabiley.xisaabso.connection.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class,
    ExperimentalMaterialApi::class
)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")


@Composable
fun Customers(navController: NavController, data: Data) {
    val context       = LocalContext.current
    val scaffoldState = rememberBottomSheetScaffoldState()

    val scope         = rememberCoroutineScope()
    val person        = StoreUserName(context)
    val customerID    = person.getCustomerID.collectAsState(initial = "")
    val KaJar         = person.getKaJarButton.collectAsState(initial = "")
    val KuShub        = person.getKuShubButton.collectAsState(initial = "")
    val Sarrif        = person.getSarrifButton.collectAsState(initial = "")
    val Money         = person.getMoneyValue.collectAsState(initial = "")


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        topBar = {
            TopAppBar(
                title = { Text(text = "Customers") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = Color.Transparent,
                //backgroundColor = MaterialTheme.colors.primary,
                //contentColor = MaterialTheme.colors.primary,
                elevation = 0.dp
            )
        },
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    //.fillMaxHeight()
                    .fillMaxHeight()
                    .padding(15.dp),
                //.background(Color.Gray),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val openDialog               = remember { mutableStateOf(false) }
                val openDialog_DhigashoUSD   = remember { mutableStateOf(false) }
                val openDialog_Sarif_To_SLSH = remember { mutableStateOf(false) }
                val accept                   = remember { mutableStateOf(false) }
                val loading                  = remember { mutableStateOf(false) }

                Text(
                    text = data.FirstName,
                    fontSize = 20.sp,
                    modifier = Modifier
                        //.background(Color.Gray)
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                if(loading.value) {
                    AlertDialog(
                        onDismissRequest = { /* openDialog.value = false */ },
                        title = {
                            Text("Here is a description text of the dialog")
                        },
                        text = {
                            Text("Here is a description text of the dialog")
                        },
                        confirmButton = {},
                        dismissButton = {}
                    )
                }

                if(accept.value) {
                    AlertDialog(
                        onDismissRequest = { /* openDialog.value = false */ },
                        title = {
                            Text("Here is a description text of the dialog")
                        },
                        text = {
                            Text("Here is a description text of the dialog")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    accept.value                   = false
                                    openDialog.value               = false
                                    openDialog_DhigashoUSD.value   = false
                                    openDialog_Sarif_To_SLSH.value = false

                                    if(KaJar.value == "true") {
                                        val queue = Volley.newRequestQueue(context)
                                        val url = "https://xisaabso.online/Iib_SLSH"

                                        // Request a string response from the provided URL.
                                        val sr: StringRequest = @RequiresApi(Build.VERSION_CODES.O)
                                        object : StringRequest(
                                            Method.POST, url,
                                            Response.Listener {
                                                //Toast.makeText(context, it, Toast.LENGTH_LONG).show()

                                                when (it) {
                                                    "Finished" -> {
                                                        Toast.makeText(context, "Finished", Toast.LENGTH_LONG).show()
                                                    }

                                                    "inta aad galisay way ka badan yahay inta hadhay" -> {
                                                        Toast.makeText(
                                                            context,
                                                            "inta aad galisay way ka badan yahay inta hadhay",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }

                                                    "Successful Added to Database" -> {

                                                        //delay(500)

                                                        navController.navigate(Screen.AllVouchers_By_Transactions.route) {
                                                            popUpTo(Screen.Home.route) {
                                                                // Sida caadiga ah waa (true)
                                                                inclusive = false
                                                            }
                                                        }

                                                        val noti = Notfication(
                                                            context,
                                                            "Payment This Month",
                                                            "Waxad ka jartay: " + Money.value
                                                        )
                                                        noti.firNotification()

                                                        scope.launch {
                                                            person.saveMoneyValue("")
                                                            person.save_KaJar("false")
                                                        }

                                                    }
                                                }
                                            },
                                            Response.ErrorListener {
                                                //your error
                                            }) {
                                            override fun getParams(): Map<String, String> {
                                                val params: MutableMap<String, String> = HashMap()

                                                //params["SessionID"] = sessionID.value.toString()
                                                params["CustomerID"] = customerID.value.toString()
                                                params["Slsh"]       = Money.value.toString()
                                                return params

                                            }
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

                                    if(KuShub.value == "true") {
                                        val queue = Volley.newRequestQueue(context)
                                        val url = "https://xisaabso.online/Dhigasho_USD"

                                        // Request a string response from the provided URL.
                                        val sr: StringRequest = @RequiresApi(Build.VERSION_CODES.O)
                                        object : StringRequest(
                                            Method.POST, url,
                                            Response.Listener { response ->
                                                //Toast.makeText(context, response, Toast.LENGTH_LONG).show()

                                                if(response == "Successful Added to Database") {
                                                    Toast.makeText(context, "Lacag baad ugu shubtay", Toast.LENGTH_LONG).show()

                                                    scope.launch {
                                                        person.saveMoneyValue("")
                                                        person.save_KuShub("false")
                                                    }

                                                    val noti = Notfication(
                                                        context,
                                                        "Payment This Month",
                                                        "Waxad ku Shubta: $" + Money.value
                                                    )
                                                    noti.firNotification()

                                                }
                                            },
                                            Response.ErrorListener {
                                                //your error
                                            }) {
                                            override fun getParams(): Map<String, String> {
                                                val params: MutableMap<String, String> = HashMap()

                                                params["CustomerID"] = customerID.value.toString()
                                                params["USD"]        = Money.value.toString()
                                                return params

                                            }

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

                                    if(Sarrif.value == "true") {
                                        val queue = Volley.newRequestQueue(context)
                                        val url = "https://xisaabso.online/Sarrif_to_slsh"

                                        // Request a string response from the provided URL.
                                        val sr: StringRequest = @RequiresApi(Build.VERSION_CODES.O)
                                        object : StringRequest(
                                            Method.POST, url,
                                            Response.Listener { response -> Toast.makeText(context, response, Toast.LENGTH_LONG).show()

                                                if(response == "Successful Added to Database") {
                                                    Toast.makeText(context, "Lacag baad u sariftay", Toast.LENGTH_LONG).show()

                                                    scope.launch {
                                                        person.saveMoneyValue("")
                                                        person.save_Sarrif("false")
                                                    }

                                                    val noti = Notfication(
                                                        context,
                                                        "Payment This Month",
                                                        "Waxad U Sarriftay: $" + Money.value
                                                    )
                                                    noti.firNotification()
                                                }
                                            },
                                            Response.ErrorListener {
                                                //your error
                                            }) {
                                            override fun getParams(): Map<String, String> {
                                                val params: MutableMap<String, String> = HashMap()

                                                params["CustomerID"] = customerID.value.toString()
                                                params["USD"]        = Money.value.toString()
                                                return params

                                            }

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
                                }
                            ) {
                                Text("Confirm Button")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    accept.value = false

                                    scope.launch {
                                        //Toast.makeText(context, data.ID.toString(), Toast.LENGTH_SHORT).show()
                                        person.saveMoneyValue("")
                                    }
                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )

                }

                //////////////////////////////////////////////
                ////////////////    Iib SLSH   ///////////////
                //////////////////////////////////////////////

                if(openDialog.value){
                    val usdMoney = remember { mutableStateOf(TextFieldValue("")) }

                    AlertDialog(
                        onDismissRequest = {
                            //openDialog.value = false
                        },
                        title = {
                            OutlinedTextField(
                                //value = usdMoney.value,
                                value = usdMoney.value,

                                onValueChange = { it ->
                                    //usdMoney.value = it
                                    usdMoney.value = it.copy(it.text.filter { it.isDigit() })
                                },


                                label = { Text(text = "Enter SLSH...") },
                                placeholder = { Text(text = "Enter Dollar") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                textStyle = TextStyle(fontSize = 29.sp),
                                visualTransformation = { annotatedString ->
                                    priceFilter(annotatedString.text)
                                }
                            )
                        },
                        text = {
                            //Text("Here is a description text of the dialog")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    //openDialog.value = false
                                    //navController.navigate(Screen.AllVouchers_By_Transactions.route)
                                    if(usdMoney.value.text.isBlank()) {
                                        Toast.makeText(
                                            context,
                                            "Wuu madhan yahay (ANDROID)",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    else {
                                        // Only USD Payment
                                        if (usdMoney.value.text.isNotBlank()) {
                                            accept.value = true

                                            scope.launch {
                                                person.saveMoneyValue(usdMoney.value.text)
                                            }
                                        }
                                    }
                                }
                            ) {
                                Text("Confirm Button")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    openDialog.value = false

                                    scope.launch {
                                        person.save_KaJar("false")
                                    }

                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        openDialog.value = true

                        scope.launch {
                            person.save_KaJar("true")
                            //person.save_KuShub("false")
                            //person.save_Sarrif("false")
                        }

                    }
                ) {
                    Text(
                        text = "Ka Jar",
                        modifier = Modifier.padding(20.dp),
                        fontSize = 25.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))


                //////////////////////////////////////////////
                ///////////////   Dhigasho USD   /////////////
                //////////////////////////////////////////////

                if(openDialog_DhigashoUSD.value){
                    val dhigashoUSD = remember { mutableStateOf(TextFieldValue("")) }

                    AlertDialog(
                        onDismissRequest = {
                            //openDialog_Dhigasho_USD.value = false
                        },
                        title = {
                            OutlinedTextField(
                                value = dhigashoUSD.value,
                                onValueChange = { dhigashoUSD.value = it },
                                label = { Text(text = "Dhigasho USD...") },
                                placeholder = { Text(text = "Enter Dhigasho USD") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                textStyle = TextStyle(fontSize = 29.sp),
                                visualTransformation = { annotatedString ->
                                    priceFilter(annotatedString.text)
                                }
                            )
                        },
                        text = {
                            //Text("Here is a description text of the dialog")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    if(dhigashoUSD.value.text.isBlank()) {
                                        Toast.makeText(
                                            context,
                                            "Wuu madhan yahay (ANDROID)",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    else {
                                        // Only USD Payment
                                        if (dhigashoUSD.value.text.isNotBlank()) {
                                            accept.value = true

                                            scope.launch {
                                                person.saveMoneyValue(dhigashoUSD.value.text)
                                            }
                                        }
                                    }
                                }
                            ) {
                                Text("Confirm Button")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    openDialog_DhigashoUSD.value = false

                                    scope.launch {
                                        person.save_KuShub("false")
                                    }
                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        openDialog_DhigashoUSD.value = true

                        scope.launch {
                            person.save_KuShub("true")
                        }
                    }
                ) {
                    Text(
                        text = "Ku Shub",
                        modifier = Modifier.padding(20.dp),
                        fontSize = 25.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))


                //////////////////////////////////////////////
                //////////////   Sarif To SLSH   /////////////
                //////////////////////////////////////////////


                if(openDialog_Sarif_To_SLSH.value){
                    val sarifToSLSH = remember { mutableStateOf(TextFieldValue("")) }

                    AlertDialog(
                        onDismissRequest = {
                            //openDialog_Sarif_To_SLSH.value = false
                        },
                        title = {
                            OutlinedTextField(
                                value = sarifToSLSH.value,
                                onValueChange = { sarifToSLSH.value = it },
                                label = { Text(text = "Sarrif USD...") },
                                placeholder = { Text(text = "Enter Sarrif USD") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                textStyle = TextStyle(fontSize = 29.sp),
                                visualTransformation = { annotatedString ->
                                    priceFilter(annotatedString.text)
                                }
                            )
                        },
                        text = {
                            //Text("Here is a description text of the dialog")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    if(sarifToSLSH.value.text.isBlank()) {
                                        Toast.makeText(
                                            context,
                                            "Wuu madhan yahay (ANDROID)",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    else {
                                        // Only USD Payment
                                        if (sarifToSLSH.value.text.isNotBlank()) {
                                            accept.value = true

                                            scope.launch {
                                                person.saveMoneyValue(sarifToSLSH.value.text)
                                            }
                                        }
                                    }
                                }
                            ) {
                                Text("Confirm Button")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    openDialog_Sarif_To_SLSH.value = false

                                    scope.launch {
                                        person.save_Sarrif("false")
                                    }

                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        openDialog_Sarif_To_SLSH.value = true

                        scope.launch {
                            person.save_Sarrif("true")
                        }

                    }
                ) {
                    Text(
                        text = "Sarrif",
                        modifier = Modifier.padding(20.dp),
                        fontSize = 25.sp
                    )
                }

                //////////////////////////////////////////////
                //////////////////////////////////////////////
                //////////////////////////////////////////////

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddCustomer.route)
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(Icons.Filled.Add , "ddd")
            }
        }
    ) {
        ConnectivityStatus_Customers(navController, scaffoldState)
    }


}


@OptIn(ExperimentalMaterialApi::class)
@ExperimentalCoroutinesApi
@Composable
fun ConnectivityStatus_Customers(
    navController: NavController,
    scaffoldState: BottomSheetScaffoldState
) {
    // This will cause re-composition on every network state change
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    if (isConnected) {
        Xisaabso_Customers(navController, scaffoldState)
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


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("MutableCollectionMutableState")
@Composable
fun Xisaabso_Customers(
    navController: NavController,
    scaffoldState: BottomSheetScaffoldState
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        //.background(MainScreenColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //val viewModel: MyViewModel = viewModel()
        //val isRefreshing by viewModel.isRefreshing.collectAsState()

        val view_Model = hiltViewModel<Xisaabso_CustomerDataViewModel>()
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
                items(pagingData) {
                    Cards_Customers(
                        data = it!!,
                        navController,
                        scaffoldState,
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Cards_Customers(
    data: Data,
    navController: NavController,
    scaffoldState: BottomSheetScaffoldState
) {
    val context = LocalContext.current
    //val REQUEST_PHONE_CALL = 1
    val scope  = rememberCoroutineScope()
    val person = StoreUserName(context)

    Card(
        modifier = Modifier
            .padding(
                start = 0.dp,
                end = 0.dp,
                top = 0.dp,
                bottom = 10.dp
            )
            //.fillMaxWidth()
            .fillMaxSize()
            .clickable {
                scope.launch {
                    //Toast.makeText(context, data.ID.toString(), Toast.LENGTH_SHORT).show()
                    person.saveCustomerID(data.ID.toString())

                    if (scaffoldState.bottomSheetState.isCollapsed) {
                        scaffoldState.bottomSheetState.expand()
                    }
                    else {
                        scaffoldState.bottomSheetState.collapse()
                    }

                    /*
                    navController.navigate(
                        Screen.CustomerDetails_Screen.route +
                                "/${data.FirstName + " " + data.SecondName + " " + data.ThirdName}" +
                                "/${data.Baaqi_SLSH}" +
                                "/${data.Baaqi_USD}" +
                                "/${data.Total_Iibka_SLSH}"
                    )
                    */

                }
                //navController.navigate(Screen.CustomerDetails_Screen.route)
            },
        RoundedCornerShape(0.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                //.background(Color.Green)
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                //.background(Color.Cyan)
            ){
                IconButton(
                    onClick = {
                        //Toast.makeText(context, "welcome", Toast.LENGTH_SHORT).show()

                        scope.launch {
                            //Toast.makeText(context, data.ID.toString(), Toast.LENGTH_SHORT).show()
                            person.saveCustomerID(data.ID.toString())

                            if (scaffoldState.bottomSheetState.isCollapsed) {
                                scaffoldState.bottomSheetState.expand()
                            }
                            else {
                                scaffoldState.bottomSheetState.collapse()
                            }
                        }

                    },
                    modifier = Modifier
                        .size(35.dp)
                ) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                //.background(Color.LightGray)
            ) {
                Text(
                    text = "#" + data.ID,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = data.ID.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.padding(7.dp))


            Text(
                text = data.FirstName + " "
                        + data.SecondName + " "
                        + data.ThirdName + " ",
                //+ data.LastName + "(" + data.ID.toString() + ")",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))


            ///////////////////////////////////////////////////
            ///////////////////////////////////////////////////
            ///////////////////////////////////////////////////

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
                        text = "$" + data.Baaqi_USD,
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
                        text = "" + data.Baaqi_SLSH,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    } //END Card
}



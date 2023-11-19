package com.gabiley.xisaabso.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabiley.xisaabso.Classes.Screen
import com.gabiley.xisaabso.DataStore.StoreUserName
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Screen_A_with_WithTopBar(navController: NavController) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, "backIcon")
                    }
                },
                backgroundColor = Color.Transparent,
                //backgroundColor = MaterialTheme.colors.primary,
                //contentColor = MaterialTheme.colors.primary,
                elevation = 0.dp
            )
        },
        drawerContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Drawer content")
                Spacer(Modifier.height(20.dp))



                val context = LocalContext.current
                //val scope = rememberCoroutineScope()

                // we instantiate the saveEmail class
                val person = StoreUserName(context)

                Button(

                    onClick = {
                        scope.launch {

                            person.saveActiveScreen("Screen_A")

                            //Open_Screen_A.value = true
                            //Open_Screen_B.value = false
                            //scaffoldState.drawerState.close()
                        }
                    }
                ) {
                    Text("Open Screen A")
                }

                Button(
                    onClick = {
                        scope.launch {
                            person.saveActiveScreen("Screen_B")

                            //scaffoldState.drawerState.close()
                        }
                    }
                ) {
                    Text("Open Screen B")
                }

            }
        },
        sheetContent = {}
    ){
        //val context = LocalContext.current
        //val person = StoreUserName(context)
        //val activeScreen = person.getActiveScreen.collectAsState(initial = "")

        Screen_A(navController)

    }
}



@Composable
fun Screen_A(
    navController: NavController
) {
    val context = LocalContext.current
    //val shared = passwordViewModel.person

    val scope = rememberCoroutineScope()
    // we instantiate the saveEmail class
    //val person = StoreUserName(context)


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            //.background(Color.Blue)
        //.background(colorResource(id = R.color.QuruxColor))
        //.padding(top = 30.dp, bottom = 30.dp)
    ) {
        MyUI()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = com.gabiley.xisaabso.R.color.quruxColor))
                .padding(top = 30.dp, bottom = 30.dp)
        ) {

            Text(text = "Screen A", fontSize = 33.sp, color = Color.White)

            //Text(text = Result_Username.value.toString(), fontSize = 33.sp, color = Color.White)
            //Text(text = Result_Password.value.toString(), fontSize = 33.sp, color = Color.White)
            //Text(text = Result_AccountStatus.value.toString(), fontSize = 33.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
            //.background(Color.Cyan)
        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .clickable {
                        navController.navigate(Screen.Customers_Screen.route)
                    },
                RoundedCornerShape(7.dp),
                elevation = 5.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Customers"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Customers",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .clickable {
                        navController.navigate(
                            Screen.AllVouchers_By_Transactions.route
                        )
                    },
                RoundedCornerShape(7.dp),
                elevation = 5.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Vouchers"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Vouchers",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } // END Column

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
            //.background(Color.Cyan)

        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .clickable {
                        navController.navigate(Screen.Dashboard_Screen.route)
                    },
                RoundedCornerShape(7.dp),
                elevation = 5.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Dashboard"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Dashboard",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .clickable {
                        navController.navigate(Screen.Whatsapp_Msg_Screen.route)
                    },
                RoundedCornerShape(7.dp),
                elevation = 5.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Whatsapp"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Whatsapp",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = {
            //open_mainDialog.value = true
        }) {
            Text(text = "open", fontSize = 22.sp)
        }

    }
}


@Composable
fun Screen_B(
    navController: NavController
) {
    val context = LocalContext.current
    //val shared = passwordViewModel.person

    val scope = rememberCoroutineScope()
    // we instantiate the saveEmail class
    //val person = StoreUserName(context)

    val open_mainDialog = remember {
        mutableStateOf(false)
    }

    if(open_mainDialog.value) {
        Screen_A(navController)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
        //.background(Color.Blue)
        //.background(colorResource(id = R.color.QuruxColor))
        //.padding(top = 30.dp, bottom = 30.dp)
    ) {
        MyUI()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = com.gabiley.xisaabso.R.color.quruxColor))
                .padding(top = 30.dp, bottom = 30.dp)
        ) {

            Text(text = "Screen B", fontSize = 33.sp, color = Color.White)

            //Text(text = Result_Username.value.toString(), fontSize = 33.sp, color = Color.White)
            //Text(text = Result_Password.value.toString(), fontSize = 33.sp, color = Color.White)
            //Text(text = Result_AccountStatus.value.toString(), fontSize = 33.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
            //.background(Color.Cyan)
        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .clickable {
                        navController.navigate(Screen.Customers_Screen.route)
                    },
                RoundedCornerShape(7.dp),
                elevation = 5.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Customers"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Customers",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .clickable {
                        navController.navigate(
                            Screen.AllVouchers_By_Transactions.route
                        )
                    },
                RoundedCornerShape(7.dp),
                elevation = 5.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Vouchers"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Vouchers",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } // END Column

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
            //.background(Color.Cyan)

        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .clickable {
                        navController.navigate(Screen.Dashboard_Screen.route)
                    },
                RoundedCornerShape(7.dp),
                elevation = 5.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Dashboard"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Dashboard",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .clickable {
                        navController.navigate(Screen.Whatsapp_Msg_Screen.route)
                    },
                RoundedCornerShape(7.dp),
                elevation = 5.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Whatsapp"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Whatsapp",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = {
            open_mainDialog.value = true
        }) {
            Text(text = "open", fontSize = 22.sp)
        }

    }
}



//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////



@Composable
fun MyUI() {
    val optionsList = prepareOptionsList()

    LazyRow(
        //modifier = Modifier
            //.background(Color.Gray),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp)
    ) {
        items(optionsList) { item ->
            ItemLayout(optionsList = item)
        }
    }
}

// single item layout
@Composable
private fun ItemLayout(
    optionsList: OptionsList,
    context: Context = LocalContext.current.applicationContext
) {
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast
                        .makeText(context, optionsList.option, Toast.LENGTH_SHORT)
                        .show()
                }
                .padding(all = 8.dp),
        ) {
            Icon(
                modifier = Modifier.size(size = 36.dp),
                imageVector = optionsList.icon,
                contentDescription = null,
                tint = Color(0xFF7850E8)
            )
        }
    }
}

// add items to the list
private fun prepareOptionsList(): MutableList<OptionsList> {
    val optionsList = mutableListOf<OptionsList>()

    optionsList.add(OptionsList(icon = Icons.Outlined.Favorite, option = "Saved Items"))
    optionsList.add(OptionsList(icon = Icons.Outlined.AccountBox, option = "Payment History"))
    optionsList.add(OptionsList(icon = Icons.Outlined.Notifications, option = "New Ideas"))
    optionsList.add(OptionsList(icon = Icons.Outlined.Warning, option = "Items History"))
    optionsList.add(OptionsList(icon = Icons.Outlined.Favorite, option = "Shared Articles"))
    optionsList.add(OptionsList(icon = Icons.Outlined.Notifications, option = "Previous Notifications"))
    optionsList.add(OptionsList(icon = Icons.Outlined.Call, option = "Verification Badge"))
    optionsList.add(OptionsList(icon = Icons.Outlined.Favorite, option = "Pending Tasks"))
    optionsList.add(OptionsList(icon = Icons.Outlined.Favorite, option = "FAQs"))
    optionsList.add(OptionsList(icon = Icons.Outlined.Favorite, option = "Support"))

    return optionsList
}

data class OptionsList(val icon: ImageVector, val option: String)


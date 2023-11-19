package com.gabiley.xisaabso.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabiley.xisaabso.Classes.Screen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomerDetails_WithTopBar(
    navController: NavController,
    myName: String?,
    Baaqi_SLSH: String?,
    Baaqi_USD: String?,
    Total_Iibka_SLSH: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Details...")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Customers_Screen.route){
                                popUpTo(Screen.Customers_Screen.route) {
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
            CustomerDetails(
                myName,
                Baaqi_SLSH,
                Baaqi_USD,
                Total_Iibka_SLSH,
                navController,
                /*viewModel*/
            )
        }
    )
}





@Composable
fun CustomerDetails(
    myName: String?,
    Baaqi_SLSH: String?,
    Baaqi_USD: String?,
    Total_Iibka_SLSH: String?,
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = myName.toString(), fontSize = 23.sp)

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "B. SLSH: " + Baaqi_SLSH.toString(), fontSize = 25.sp)
        Text(text = "B. USD: $" + Baaqi_USD.toString(), fontSize = 25.sp)
        Text(text = "T. Iikba: SLSH " + Total_Iibka_SLSH.toString(), fontSize = 25.sp)



    }


    
    
    
}
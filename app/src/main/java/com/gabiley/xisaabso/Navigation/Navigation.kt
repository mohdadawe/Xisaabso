package com.gabiley.xisaabso.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gabiley.xisaabso.Classes.Customer_data.Data
import com.gabiley.xisaabso.Classes.Screen
import com.gabiley.xisaabso.DataStore.StoreUserName
import com.gabiley.xisaabso.MyViewModel.PasswordViewModel
import com.gabiley.xisaabso.Screens.*
import com.gabiley.xisaabso.Screens.Xis_Vouchers.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Nav(data: Data) {
   val context = LocalContext.current
   val navController = rememberNavController()

   // we instantiate the saveEmail class
   val person = StoreUserName(context)
   val verificationScreen = person.getVerficationCode.collectAsState(initial = "")


   NavHost(
      navController = navController,
      startDestination =
         if(verificationScreen.value == "true")
            Screen.Home.route
         else
            Screen.LoginScreen.route
         /*
         if(Result_Username.value!!.isNotBlank())
            Screen.Home.route
         else
            Screen.LoginScreen.route
         */
   ) {
      //composable(route = Screen.ChooseUsernameForLogin.route) {
         //ChooseUsernameForLogin_WithTopBar(navController)
      //}

      composable(
         route = "ChooseUsernameForLogin/{id}/{UserID}",
         arguments = listOf(
            navArgument(name = "id") {
               type = NavType.StringType
            }
         )
      ) {
         ChooseUsernameForLogin_WithTopBar(
            ID = it.arguments?.getString("id"),
            UserID = it.arguments?.getString("UserID"),
            navController
         )
      }

      composable(route = Screen.Whatsapp_Msg_Screen.route) {
         WhatsappScreen_WithTopBar(navController)
      }

      composable(route = Screen.Dashboard_Screen.route) {
         Dashboard_WithTopBar(navController)
      }

      composable(route = Screen.LoginScreen.route) {
         LoginScreen(navController)
      }

      composable(route = Screen.SearchTest.route) {
         SearchTest(navController)
      }

      composable(route = Screen.Home.route) {
         Screen_A_with_WithTopBar(navController)
      }

      composable(route = Screen.AllVouchers_By_Transactions.route) {
         Xisaabso_All_Vouchers_By_Transactions_WithTopBar(navController)
      }

      composable(
         route = "CustomerDetails_Screen/{name}/{Baaqi_SLSH}/{Baaqi_USD}/{Total_Iibka_SLSH}",
         arguments = listOf(
            navArgument(name = "name") {
               type = NavType.StringType
            }
         )
      ) {
         CustomerDetails_WithTopBar(
            navController,
            myName = it.arguments?.getString("name"),
            Baaqi_SLSH = it.arguments?.getString("Baaqi_SLSH"),
            Baaqi_USD = it.arguments?.getString("Baaqi_USD"),
            Total_Iibka_SLSH = it.arguments?.getString("Total_Iibka_SLSH")
         )
      }

      composable(
         route = "Verification_Screen/{ID}",
         arguments = listOf(
            navArgument(name = "ID") {
               type = NavType.StringType
            }
         )
      ) {
         VerificationScreen(
            navController,
            myName = it.arguments?.getString("ID"),
         )
      }


      /*
      composable(route = Screen.Verification_Screen.route) {
         VerificationScreen(navController)
      }
      */

      composable(route = Screen.Customers_Screen.route) {
         Customers(navController, data)
      }

      /*
      composable(
         route = "ChoosingAccountFromUser/{ID}",
         arguments = listOf(
            navArgument(name = "ID") {
               type = NavType.StringType
            }
         )
      ) {
         VerificationScreen(
            navController,
            myName = it.arguments?.getString("ID"),
         )
      }
      */

      composable(
         route = "ConvertUsername_Info/{ID}",
         arguments = listOf(
            navArgument(name = "ID") {
               type = NavType.StringType
            }
         )
      ) {
         VerificationScreen(
            navController,
            myName = it.arguments?.getString("ID"),
         )
      }

      composable(route = Screen.AddCustomer.route) {
         AddCustomer_with_WithTopBar(navController)
      }


   }
}



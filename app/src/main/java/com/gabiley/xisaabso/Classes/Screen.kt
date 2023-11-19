package com.gabiley.xisaabso.Classes


sealed class Screen(val route: String) {
    object Home: Screen(route = "Home")
    object AllVouchers_By_Transactions: Screen(route = "AllVouchers_By_Transactions")
    object Search_Screen: Screen(route = "Search_Screen")
    object SearchTest: Screen(route = "searchTest")
    object Customers_Screen: Screen(route = "Customers_Screen")
    object LoginScreen: Screen(route = "LoginScreen")
    //object PasswordScreen: Screen(route = "PasswordScreen")
    object CustomerDetails_Screen: Screen(route = "CustomerDetails_Screen")
    object Dashboard_Screen: Screen(route = "Dashboard_Screen")
    object Whatsapp_Msg_Screen: Screen(route = "Whatsapp_Msg_Screen")
    object Verification_Screen: Screen(route = "Verification_Screen")

    object ChooseUsernameForLogin: Screen(route = "ChooseUsernameForLogin")
    object ChoosingAccountFromUser: Screen(route = "ChoosingAccountFromUser")

    object ConvertUsername_Info: Screen(route = "ConvertUsername_Info")

    object AddCustomer: Screen(route = "AddCustomer")


}



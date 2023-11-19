package com.gabiley.xisaabso.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class StoreUserName(private val context: Context) {
   // to make sure there's only one instance
   companion object {
      private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userEmail")

      val CUSTOMER_ID    = stringPreferencesKey("customer_ID")
      val MyNameValue    = stringPreferencesKey("myName_value")
      val MoneyValue     = stringPreferencesKey("MoneyValue")
      val CustomerIDPaid = stringPreferencesKey("getCustomerIDPaid")
      val Ka_Jar_Button  = stringPreferencesKey("Ka_Jar_Button")
      val Ku_Shub_Button = stringPreferencesKey("Ku_Shub_Button")
      val Sarrif_Button  = stringPreferencesKey("Sarrif_Button")
      val USERNAME_KEY   = stringPreferencesKey("username")

      //Customer_Details
      val FullName         = stringPreferencesKey("FullName")
      val Baaqi_SLSH       = stringPreferencesKey("Baaqi_SLSH")
      val Baaqi_USD        = stringPreferencesKey("Baaqi_USD")
      val Total_Iibka_SLSH = stringPreferencesKey("Total_Iibka_SLSH")
      val VerficationCode  = stringPreferencesKey("VerficationCode")
      val ActiveScreen     = stringPreferencesKey("ActiveScreen")

      val CustomerFirstName  = stringPreferencesKey("CustomerFirstName")
      val CustomerSecondName = stringPreferencesKey("CustomerSecondName")
      val CustomerThirdName  = stringPreferencesKey("CustomerThirdName")

      //FullName
      //Baaqi_SLSH
      //Baaqi_USD
      //Total_Iibka_SLSH
   }


   //get the saved CustomerFirstName
   val getCustomerFirstName: Flow<Any?> = context.dataStore.data
      .map { preferences ->
         preferences[CustomerFirstName] ?: ""
      }

   //get the saved CustomerSecondName
   val getCustomerSecondName: Flow<Any?> = context.dataStore.data
      .map { preferences ->
         preferences[CustomerSecondName] ?: ""
      }

   //get the saved CustomerThirdName
   val getCustomerThirdName: Flow<Any?> = context.dataStore.data
      .map { preferences ->
         preferences[CustomerThirdName] ?: ""
      }


   //get the saved ActiveScreen
   val getActiveScreen: Flow<Any?> = context.dataStore.data
      .map { preferences ->
         preferences[ActiveScreen] ?: ""
      }

   //get the saved CustomerID
   val getCustomerID: Flow<Any?> = context.dataStore.data
      .map { preferences ->
         preferences[CUSTOMER_ID] ?: ""
      }

   //get the saved MoneyValue
   val getMoneyValue: Flow<String?> = context.dataStore.data
      .map { preferences ->
         preferences[MoneyValue] ?: ""
      }

   //get the saved CustomerIDPaid
   val getCustomerIDPaid: Flow<String?> = context.dataStore.data
      .map { preferences ->
         preferences[CustomerIDPaid] ?: ""
      }

   //get the saved Ka_Jar_Button
   val getKaJarButton: Flow<String?> = context.dataStore.data
      .map { preferences ->
         preferences[Ka_Jar_Button] ?: ""
      }

   //get the saved Ku_Shub_Button
   val getKuShubButton: Flow<String?> = context.dataStore.data
      .map { preferences ->
         preferences[Ku_Shub_Button] ?: ""
      }

   //get the saved Sarrif_Button
   val getSarrifButton: Flow<String?> = context.dataStore.data
      .map { preferences ->
         preferences[Sarrif_Button] ?: ""
      }

   //get the saved UserName
   val getUserName: Flow<String?> = context.dataStore.data
      .map { preferences ->
         preferences[USERNAME_KEY] ?: ""
      }

   //get the saved UserName
   val getVerficationCode: Flow<String?> = context.dataStore.data
      .map { preferences ->
         preferences[VerficationCode] ?: ""
      }



   ///////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////


   //save CustomerFirstName into datastore
   suspend fun saveCustomerFirstName(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[CustomerFirstName] = Value
      }
   }

   //save CustomerSecondName into datastore
   suspend fun saveCustomerSecondName(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[CustomerSecondName] = Value
      }
   }

   //save CustomerThirdName into datastore
   suspend fun saveCustomerThirdName(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[CustomerThirdName] = Value
      }
   }

   //save ActiveScreen into datastore
   suspend fun saveActiveScreen(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[ActiveScreen] = Value
      }
   }

   //save CustomerID into datastore
   suspend fun saveCustomerID(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[CUSTOMER_ID] = Value
      }
   }

   //save MyNameValue into datastore
   suspend fun saveMyNameValue(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[MyNameValue] = Value
      }
   }


   //save MoneyValue into datastore
   suspend fun saveMoneyValue(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[MoneyValue] = Value
      }
   }

   //save CustomerIDPaid into datastore
   suspend fun saveCustomerIDPaid(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[CustomerIDPaid] = Value
      }
   }


   //save CustomerIDPaid into datastore
   suspend fun save_KaJar(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[Ka_Jar_Button] = Value
      }
   }


   //save CustomerIDPaid into datastore
   suspend fun save_KuShub(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[Ku_Shub_Button] = Value
      }
   }


   //save CustomerIDPaid into datastore
   suspend fun save_Sarrif(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[Sarrif_Button] = Value
      }
   }

   //save UserName into datastore
   suspend fun saveUserName(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[USERNAME_KEY] = Value
      }
   }


   //save UserName into datastore
   suspend fun saveVerficationCode(Value: String) {
      context.dataStore.edit { preferences ->
         preferences[VerficationCode] = Value
      }
   }


   //FullName
   //Baaqi_SLSH
   //Baaqi_USD
   //Total_Iibka_SLSH


}


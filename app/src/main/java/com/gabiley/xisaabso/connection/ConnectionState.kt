package com.gabiley.xisaabso.connection

sealed class ConnectionState {
   object Available : ConnectionState()
   object Unavailable : ConnectionState()
}

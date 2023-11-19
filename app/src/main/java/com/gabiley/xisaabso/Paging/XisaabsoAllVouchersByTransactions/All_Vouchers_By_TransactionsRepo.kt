package com.gabiley.xisaabso.Paging.XisaabsoAllVouchersByTransactions

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gabiley.xisaabso.Paging.XisaabsoAllVouchersByTransactions.Api.ApiService_Xisaabso_All_Vouchers_By_Transactions

class Xisaabso_All_Vouchers_By_TransactionsRepo(
    private val apiserviceXisaabso_All_Vouchers_By_Transactions:
    ApiService_Xisaabso_All_Vouchers_By_Transactions
) {
    fun getXisaabso_All_Vouchers_By_TransactionsStream() = Pager(
        config = PagingConfig(
            pageSize = 5,
            prefetchDistance = 5,
            enablePlaceholders = false,
            initialLoadSize = 5
        ),

        pagingSourceFactory = {
            Xisaabso_All_Vouchers_By_TransactionsPagingSource(
                apiserviceXisaabso_All_Vouchers_By_Transactions
            )
        }
    ).flow

}


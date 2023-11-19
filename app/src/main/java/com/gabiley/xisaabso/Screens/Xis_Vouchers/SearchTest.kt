package com.gabiley.xisaabso.Screens.Xis_Vouchers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.gabiley.xisaabso.MainViewModel.MainViewModel
import com.gabiley.xisaabso.Paging.XisaabsoAllVouchersByTransactions.Xisaabso_All_Vouchers_By_TransactionsViewModel


@Composable
fun SearchTest(navController: NavController) {
    val viewModel = viewModel<MainViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val persons by viewModel.persons.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()


    val view_Model = hiltViewModel<Xisaabso_All_Vouchers_By_TransactionsViewModel>()
    val pagingData = view_Model.getDDDDD().collectAsLazyPagingItems()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if(isSearching) {
            Box(modifier = Modifier
                .fillMaxSize()
                //.background(Color.Green)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        else {
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



            /*
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(persons) { person ->
                    Text(
                        text = "${person.firstName} ${person.lastName}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        fontSize = 33.sp
                    )
                }
            }
            */

        }



    }

}
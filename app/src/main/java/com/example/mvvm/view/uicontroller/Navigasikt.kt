package com.example.myarsitekturmvvm.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm.model.DataJK.JenisK
import com.example.myarsitekturmvvm.view.FormSiswa


enum class Navigasi {
    Formulir,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaApp(
    modifier: Modifier = Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    insertData: SiswaViewModel.(List<String?>) -> Unit
){
    Scaffold { isiRuang->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,

            modifier = Modifier.padding(isiRuang)){
                composable(route = Navigasi.Formulir.name){
                    val konteks = LocalContext.current
                    FormSiswa(
                        pilihanJK = JenisK.map { id -> konteks.resources.getString(id) },
                        onSubmitButtonClicked = {
                            viewModel.insertData(it)
                            navController.navigate(Navigasi.Detail.name)
                        }
                    )
                }
                composable(route = Navigasi.Detail.name){
                    TampilSiswa(
                        StatusUiSiswa = uiState.value,
                        onBackButtonClicked = { cancelAndBackToFormulir(navController) }
                    )
                }
        }
        )
    }
}

@Composable
fun TampilSiswa(StatusUiSiswa: value, onBackButtonClicked: () -> Unit) {
    TODO("Not yet implemented")
}

private fun cancelAndBackToFormulir(
    navController: NavHostController
) {
    navController.popBackStack(Navigasi.Formulir.name, inclusive = false
}
package dev.araozu.laboratorio2

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.araozu.laboratorio2.model.Distrito
import dev.araozu.laboratorio2.model.Partido
import dev.araozu.laboratorio2.viewmodel.DistritoViewModel
import dev.araozu.laboratorio2.viewmodel.PartidoViewModel
import kotlinx.coroutines.flow.Flow

var listaDistritos = Distrito.values().let {
    it.sortBy { p -> p.name }
    it
}
@Composable
fun BotonDistrito( navController: NavController,distrito: Distrito) {
    Row(modifier = Modifier.fillMaxWidth()) {
        FilledTonalButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(
                    route = Destinations.CandidatosDistritoScreen.createRoute(
                        distrito.name
                    )
                )
            }
        ) {
            Text(
                text = distrito.toString(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp, fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DistritoInfoList(
    navController: NavController,distritoList: Flow<PagingData<Distrito>>
) {
    val distritosListItems: LazyPagingItems<Distrito> = distritoList.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Buscar por distritos",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        items(distritosListItems) {distrito->
            BotonDistrito(navController,distrito=distrito!!)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDistritos(navController: NavController, viewModel: DistritoViewModel) {
    DistritoInfoList(navController = navController, distritoList =viewModel.distritos )
}


/**
 * Renderiza una lista de botones con todos los distritos de Arequipa
 */
@Composable
fun ListDistritos(navController: NavController,viewModel: DistritoViewModel) {
   ListaDistritos(navController = navController, viewModel =viewModel )
}

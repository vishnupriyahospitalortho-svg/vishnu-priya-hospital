package com.example.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.data.model.LocationRepository
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.HospitalViewModel

@Composable
fun LocationsScreen(
    viewModel: HospitalViewModel,
    modifier: Modifier = Modifier
) {
    LocationDetailScreen(
        location = LocationRepository.HOSPITAL_MAIN,
        viewModel = viewModel,
        onBack = { viewModel.navigateTo(AppScreen.HOME) },
        modifier = modifier
    )
}

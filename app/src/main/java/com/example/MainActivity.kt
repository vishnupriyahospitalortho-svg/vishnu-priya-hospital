package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ui.components.DepartmentDetailDialog
import com.example.ui.components.DoctorDetailDialog
import com.example.ui.components.EmergencyDialog
import com.example.ui.components.HospitalBottomBar
import com.example.ui.components.HospitalTopBar
import com.example.ui.screens.DepartmentsScreen
import com.example.ui.screens.DoctorsOpdScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LocationDetailScreen
import com.example.ui.screens.LocationsScreen
import com.example.ui.screens.OrthoGuideScreen
import com.example.ui.screens.PaymentPolicyScreen
import com.example.ui.theme.VishnuPriyaTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.HospitalViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: HospitalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VishnuPriyaTheme {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainAppContent(viewModel: HospitalViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val showEmergencyDialog by viewModel.showEmergencyDialog.collectAsState()
    val selectedDoctor by viewModel.selectedDoctorDetail.collectAsState()
    val selectedDepartment by viewModel.selectedDepartment.collectAsState()
    val selectedLocation by viewModel.selectedLocation.collectAsState()

    // If a location detail is opened, handle its back press
    if (selectedLocation != null) {
        LocationDetailScreen(
            location = selectedLocation!!,
            viewModel = viewModel,
            onBack = { viewModel.closeLocationDetails() }
        )
        return
    }

    // Handle back press if not on Home screen
    if (currentScreen != AppScreen.HOME) {
        BackHandler {
            viewModel.navigateTo(AppScreen.HOME)
        }
    }

    Scaffold(
        topBar = {
            HospitalTopBar(
                onEmergencyClick = { viewModel.setEmergencyDialogVisible(true) },
                onLocationsClick = { viewModel.navigateTo(AppScreen.LOCATIONS) }
            )
        },
        bottomBar = {
            HospitalBottomBar(
                currentScreen = currentScreen,
                onNavigate = { viewModel.navigateTo(it) },
                modifier = Modifier.navigationBarsPadding()
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(
                targetState = currentScreen,
                label = "ScreenTransition"
            ) { screen ->
                when (screen) {
                    AppScreen.HOME -> HomeScreen(viewModel = viewModel)
                    AppScreen.LOCATIONS -> LocationsScreen(viewModel = viewModel)
                    AppScreen.OPD_DOCTORS -> DoctorsOpdScreen(viewModel = viewModel)
                    AppScreen.DEPARTMENTS -> DepartmentsScreen(viewModel = viewModel)
                    AppScreen.PAYMENT_POLICY -> PaymentPolicyScreen(viewModel = viewModel)
                    AppScreen.GUIDE -> OrthoGuideScreen(viewModel = viewModel)
                }
            }
        }
    }

    // Emergency Casualty Dialog
    if (showEmergencyDialog) {
        EmergencyDialog(
            onDismiss = { viewModel.setEmergencyDialogVisible(false) }
        )
    }

    // Doctor Profile Dialog
    selectedDoctor?.let { doctor ->
        DoctorDetailDialog(
            doctor = doctor,
            onDismiss = { viewModel.closeDoctorDetail() },
            onViewOpdSchedule = {
                viewModel.closeDoctorDetail()
                viewModel.navigateTo(AppScreen.OPD_DOCTORS)
            }
        )
    }

    // Department Detail Dialog
    selectedDepartment?.let { dept ->
        DepartmentDetailDialog(
            department = dept,
            onDismiss = { viewModel.closeDepartmentDetail() },
            onViewOpdSchedule = {
                viewModel.closeDepartmentDetail()
                viewModel.navigateTo(AppScreen.OPD_DOCTORS)
            }
        )
    }
}

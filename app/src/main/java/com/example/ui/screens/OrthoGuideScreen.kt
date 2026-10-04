package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ClinicalNavy
import com.example.ui.theme.EmergencyRed
import com.example.ui.theme.MedicalTealPrimary
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.HospitalViewModel

@Composable
fun OrthoGuideScreen(
    viewModel: HospitalViewModel,
    modifier: Modifier = Modifier
) {
    var selectedLocation by remember { mutableStateOf("Knee / Hip Joint") }
    var selectedDuration by remember { mutableStateOf("1 to 4 Weeks") }
    var hasSwelling by remember { mutableStateOf(false) }
    var cannotBearWeight by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("ortho_guide_screen"),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Joint Health & Self-Assessment",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MedicalTealPrimary
                    )
                )
                Text(
                    text = "Clinical guidance from Vishnu Priya Hospital Orthopedic Center",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        }

        // Self-Assessment Interactive Tool
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.HealthAndSafety, contentDescription = null, tint = MedicalTealPrimary, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Joint Pain Assessment Tool",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = MedicalTealPrimary)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("1. Where is the discomfort located?", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    val locations = listOf("Knee / Hip Joint", "Back & Spine", "Shoulder / Neck", "Ankle / Foot")
                    locations.chunked(2).forEach { rowLocs ->
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            rowLocs.forEach { loc ->
                                FilterChip(
                                    selected = selectedLocation == loc,
                                    onClick = { selectedLocation = loc },
                                    label = { Text(loc, fontSize = 11.sp) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("2. Duration of Symptoms:", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    val durations = listOf("Few Days", "1 to 4 Weeks", "3+ Months (Chronic)")
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        durations.forEach { dur ->
                            FilterChip(
                                selected = selectedDuration == dur,
                                onClick = { selectedDuration = dur },
                                label = { Text(dur, fontSize = 10.sp) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("3. Key Warning Signs:", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip(
                            selected = hasSwelling,
                            onClick = { hasSwelling = !hasSwelling },
                            label = { Text("Visible Swelling", fontSize = 11.sp) },
                            modifier = Modifier.weight(1f)
                        )
                        FilterChip(
                            selected = cannotBearWeight,
                            onClick = { cannotBearWeight = !cannotBearWeight },
                            label = { Text("Cannot Bear Weight", fontSize = 11.sp) },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(12.dp))

                    // Clinical Recommendation
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (cannotBearWeight) MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.6f) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (cannotBearWeight) Icons.Default.Warning else Icons.Default.Info,
                                    contentDescription = null,
                                    tint = if (cannotBearWeight) EmergencyRed else MedicalTealPrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (cannotBearWeight) "Urgent Clinical Attention Recommended" else "Clinical Recommendation",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (cannotBearWeight) EmergencyRed else MedicalTealPrimary
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (cannotBearWeight) {
                                    "Inability to bear weight on the $selectedLocation suggests potential ligament tear, fracture, or acute internal derangement. Immediate 24/7 casualty evaluation or digital X-Ray at Vishnu Priya Hospital is advised."
                                } else {
                                    "For $selectedLocation symptoms persisting for $selectedDuration, clinical examination with Dr. Naresh along with weight-bearing X-rays can accurately detect early osteoarthritis, meniscus strain, or disc herniation."
                                },
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, lineHeight = 16.sp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { viewModel.navigateTo(AppScreen.OPD_DOCTORS) },
                        colors = ButtonDefaults.buttonColors(containerColor = MedicalTealPrimary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().height(42.dp)
                    ) {
                        Icon(imageVector = Icons.Default.AccessTime, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("View Dr. Naresh OPD Timings & Info", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Daily Ortho Exercises & Posture Guide
        item {
            Text(
                text = "Orthopedic Rehabilitation Exercises",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = MedicalTealPrimary)
            )
            Text(
                text = "Simple clinician-recommended daily mobility protocols",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
        }

        item {
            ExerciseCard(
                title = "Quadriceps Sets (Knee Strengthening)",
                target = "Knee Joint & Patellar Tracking",
                instructions = "Sit upright with legs extended straight on a firm surface. Tighten your thigh muscles by pressing the back of your knee down against the surface. Hold for 5 seconds, relax, repeat 15 times."
            )
        }

        item {
            ExerciseCard(
                title = "Lumbar Extension & Spine Arching",
                target = "Lower Back, Sciatica & Disc Relief",
                instructions = "Lie flat on your stomach with hands under shoulders. Slowly push upper torso up while keeping pelvis on the mat. Hold for 3-5 seconds and lower gently. Perform 10 repetitions."
            )
        }

        item {
            ExerciseCard(
                title = "Cervical Retractions (Chin Tucks)",
                target = "Neck Pain & Smartphone Posture Strain",
                instructions = "Sit straight looking forward. Gently pull your chin straight back as if making a double chin without tilting head up or down. Hold for 5 seconds. Repeat 10 times."
            )
        }

        item {
            ExerciseCard(
                title = "Hamstring Stretch (Seated)",
                target = "Hip Mobility & Pelvic Alignment",
                instructions = "Sit on the edge of a sturdy chair with one leg extended straight, heel on the floor. Keeping back straight, lean forward from the hips until you feel a mild stretch behind the thigh. Hold 20 seconds."
            )
        }
    }
}

@Composable
private fun ExerciseCard(
    title: String,
    target: String,
    instructions: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(ClinicalNavy.copy(alpha = 0.12f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.FitnessCenter, contentDescription = null, tint = ClinicalNavy, modifier = Modifier.size(16.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                    Text(text = "Target: $target", style = MaterialTheme.typography.labelSmall.copy(color = MedicalTealPrimary, fontWeight = FontWeight.SemiBold, fontSize = 10.sp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = instructions,
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, lineHeight = 16.sp)
            )
        }
    }
}

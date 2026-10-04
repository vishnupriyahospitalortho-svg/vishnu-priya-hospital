package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HospitalInfo
import com.example.ui.theme.EmergencyRed
import com.example.ui.theme.MedicalNavy
import com.example.ui.theme.MedicalTealPrimary
import com.example.ui.utils.IntentUtils
import com.example.ui.viewmodel.HospitalViewModel

@Composable
fun PaymentPolicyScreen(
    viewModel: HospitalViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
            .testTag("payment_policy_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            PaymentHeader()
        }

        item {
            NoInsuranceNoticeBanner()
        }

        item {
            AcceptedPaymentModesSection()
        }

        item {
            WhyDirectPaymentCard()
        }

        item {
            BillingAssistanceCard(
                onCall = { IntentUtils.dialPhoneNumber(context, HospitalInfo.PHONE_PRIMARY) },
                onDirections = { IntentUtils.openGoogleMaps(context) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PaymentHeader() {
    Column {
        Text(
            text = "Billing & Payment Policy",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MedicalNavy
            )
        )
        Text(
            text = "Transparent Direct Billing System • Vishnu Priya Hospital, Korutla",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Composable
private fun NoInsuranceNoticeBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("no_insurance_notice_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = EmergencyRed.copy(alpha = 0.08f)
        ),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, EmergencyRed.copy(alpha = 0.4f))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = EmergencyRed,
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Block,
                            contentDescription = "No Insurance Claims",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "DIRECT PAYMENT ONLY",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = EmergencyRed,
                            letterSpacing = 0.5.sp
                        )
                    )
                    Text(
                        text = "No Insurance / Mediclaim Claims Accepted",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = EmergencyRed.copy(alpha = 0.9f)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Please note that Vishnu Priya Hospital does NOT process cashless health insurance, TPA claims, private mediclaim policies, or Arogyasri / government health welfare scheme claims.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "All outpatient consultations, digital X-Rays, laboratory investigations, surgical procedures, inpatient admissions, and pharmacy purchases are strictly payable on a direct payment basis by the patient or their attendant at the hospital billing desk.",
                style = MaterialTheme.typography.bodySmall.copy(
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Composable
private fun AcceptedPaymentModesSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("accepted_payment_modes_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = MedicalTealPrimary,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Accepted Payment Methods",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MedicalNavy
                    )
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "We accept multiple hassle-free digital and direct cash options at our billing counter:",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            PaymentMethodItem(
                icon = Icons.Default.QrCode,
                title = "UPI & QR Code Payments",
                subtitle = "Google Pay, PhonePe, Paytm, BHIM, Amazon Pay & any bank UPI apps via counter QR code.",
                badge = "Instant"
            )

            Spacer(modifier = Modifier.height(10.dp))

            PaymentMethodItem(
                icon = Icons.Default.Payments,
                title = "Cash Payment",
                subtitle = "Direct Indian Rupees cash accepted at the Ground Floor billing & registration desk.",
                badge = "Standard"
            )

            Spacer(modifier = Modifier.height(10.dp))

            PaymentMethodItem(
                icon = Icons.Default.CreditCard,
                title = "Debit & Credit Cards",
                subtitle = "All major Visa, MasterCard, and RuPay debit & credit cards accepted on POS terminal.",
                badge = "Secure"
            )

            Spacer(modifier = Modifier.height(10.dp))

            PaymentMethodItem(
                icon = Icons.Default.Receipt,
                title = "Itemized Official Receipts",
                subtitle = "Computerized, itemized official receipt and tax invoice issued for every medical transaction.",
                badge = "Transparent"
            )
        }
    }
}

@Composable
private fun PaymentMethodItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    badge: String
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MedicalTealPrimary.copy(alpha = 0.15f),
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MedicalTealPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = MedicalTealPrimary.copy(alpha = 0.12f)
                    ) {
                        Text(
                            text = badge,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MedicalTealPrimary,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 16.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun WhyDirectPaymentCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MedicalNavy.copy(alpha = 0.04f)
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, MedicalNavy.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "Why Direct Billing?",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MedicalNavy
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            DirectBenefitRow(
                title = "Affordable & Fair Hospital Pricing",
                desc = "We maintain economical, standardized charges for consultations, X-Rays, and surgeries without inflated third-party administrative markups."
            )

            Spacer(modifier = Modifier.height(8.dp))

            DirectBenefitRow(
                title = "Immediate Treatment & Zero Delays",
                desc = "Crucial trauma, fracture, and orthopaedic interventions are initiated immediately without waiting hours for insurer pre-authorizations or rejection disputes."
            )

            Spacer(modifier = Modifier.height(8.dp))

            DirectBenefitRow(
                title = "Complete Billing Transparency",
                desc = "Every fee, test, and medicine is clearly explained beforehand and documented in your printed receipt at discharge."
            )
        }
    }
}

@Composable
private fun DirectBenefitRow(title: String, desc: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "•",
            fontWeight = FontWeight.Bold,
            color = MedicalTealPrimary,
            fontSize = 18.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 17.sp
                )
            )
        }
    }
}

@Composable
private fun BillingAssistanceCard(
    onCall: () -> Unit,
    onDirections: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "Have Questions About Charges or Estimates?",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MedicalNavy
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Our hospital reception and billing desk are happy to provide upfront cost estimates for procedures, joint replacements, and room tariffs.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ElevatedButton(
                    onClick = onCall,
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MedicalTealPrimary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("call_billing_desk_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Call Billing Desk", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = onDirections,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("billing_directions_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Hospital Location", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

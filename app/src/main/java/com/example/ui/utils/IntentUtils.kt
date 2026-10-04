package com.example.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.data.model.HospitalInfo

object IntentUtils {

    fun openDialer(context: Context, phoneNumber: String = HospitalInfo.PHONE_PRIMARY) {
        try {
            val cleanPhone = phoneNumber.replace(" ", "")
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$cleanPhone")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open dialer: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialPhoneNumber(context: Context, phoneNumber: String = HospitalInfo.PHONE_PRIMARY) {
        openDialer(context, phoneNumber)
    }

    fun openGoogleMaps(context: Context, mapsUrl: String = HospitalInfo.GOOGLE_MAPS_URL) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open maps: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWhatsApp(
        context: Context,
        phoneNumber: String = "919515574014",
        message: String = "Hello Vishnu Priya Hospital, I would like to inquire about orthopedic consultation."
    ) {
        try {
            val encodedMsg = Uri.encode(message)
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=$encodedMsg")
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open WhatsApp: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    fun shareHospitalDetails(context: Context) {
        try {
            val shareText = """
                🏥 *${HospitalInfo.NAME}*
                ${HospitalInfo.TAGLINE}
                
                📍 Address: ${HospitalInfo.ADDRESS}
                📞 Emergency: ${HospitalInfo.PHONE_PRIMARY_DISPLAY} / ${HospitalInfo.PHONE_SECONDARY_DISPLAY}
                🗺️ Location: ${HospitalInfo.GOOGLE_MAPS_URL}
                
                Consult Dr. Naresh (Senior Orthopedic Surgeon) for Joint Replacement, Fractures & Spine Care.
            """.trimIndent()

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, HospitalInfo.NAME)
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            context.startActivity(Intent.createChooser(intent, "Share Hospital Details"))
        } catch (e: Exception) {
            Toast.makeText(context, "Share failed: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }
}

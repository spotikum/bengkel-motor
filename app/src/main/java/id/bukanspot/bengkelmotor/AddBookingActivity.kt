package id.bukanspot.bengkelmotor

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddBookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_booking)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")

//        val ref = Firebase.database("https://bengkel-motor-cea56-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        val database = FirebaseDatabase.getInstance("https://bengkel-motor-cea56-default-rtdb.asia-southeast1.firebasedatabase.app")
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
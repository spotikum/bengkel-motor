package id.bukanspot.bengkelmotor

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddBookingActivity  : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var etEmpPlat: EditText
    private lateinit var etEmpKeluhan: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_booking)

        etEmpName = findViewById(R.id.etEmpName)
        etEmpPlat = findViewById(R.id.etEmpPlat)
        etEmpKeluhan = findViewById(R.id.etEmpKeluhan)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance("https://bengkel-motor-cea56-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Bookings")

        btnSaveData.setOnClickListener {
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData() {

        //getting values
        val empName = etEmpName.text.toString()
        val empAge = etEmpPlat.text.toString()
        val empSalary = etEmpKeluhan.text.toString()

        if (empName.isEmpty()) {
            etEmpName.error = "Isi Kolom Nama"
        }
        if (empAge.isEmpty()) {
            etEmpPlat.error = "Isi Kolom Plat"
        }
        if (empSalary.isEmpty()) {
            etEmpKeluhan.error = "Isi Kolom Keluhan"
        }

        val empId = dbRef.push().key!!

        val employee = BookingModel(empId, empName, empAge, empSalary)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etEmpName.text.clear()
                etEmpPlat.text.clear()
                etEmpKeluhan.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}
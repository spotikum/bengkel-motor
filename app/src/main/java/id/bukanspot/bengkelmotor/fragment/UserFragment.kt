package id.bukanspot.bengkelmotor.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import id.bukanspot.bengkelmotor.LoginActivity
import id.bukanspot.bengkelmotor.R
import id.bukanspot.bengkelmotor.databinding.FragmentUserBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {

    private var _binding : FragmentUserBinding? = null
    lateinit var auth : FirebaseAuth
    private lateinit var imgUri : Uri

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        //kondisi user sedang login atau tidak
        if (user != null){
            binding.edtEmail.setText(user.email)
            binding.edtName.setText(user.uid)
        }

        //button buat logout
        binding.btnLogout.setOnClickListener {
            btnLogout()
        }
//
//        //button buat verifikasi email
//        binding.btnVerify.setOnClickListener {
//            emailVerification()
//        }
//
//        //button change pass
//        binding.btnChangePass.setOnClickListener {
//            changePass()
//        }
//
//        //button ganti email
//        binding.btnChangeEmail.setOnClickListener {
//            changeEmail()
//        }
//
//        binding.btnScanQR.setOnClickListener {
//            scanQRCode()
//        }
    }

//    private fun scanQRCode() {
//        val intent = Intent(context, CodeScannerActivity::class.java)
//        startActivity(intent)
//    }
//
//    private fun changeEmail() {
//        val intent = Intent(context, ChangeEmailActivity::class.java)
//        startActivity(intent)
//    }
//
//    private fun changePass() {
//        auth = FirebaseAuth.getInstance()
//        val user = auth.currentUser
//
//        binding.cvCurrentPass.visibility = View.VISIBLE
//
//        binding.btnCancel.setOnClickListener {
//            binding.cvCurrentPass.visibility = View.GONE
//        }
//
//        binding.btnConfirm.setOnClickListener btnConfirm@{
//
//            val pass = binding.edtCurrentPassword.text.toString()
//
//            if (pass.isEmpty()) {
//                binding.edtCurrentPassword.error = "Password Tidak Boleh Kosong"
//                binding.edtCurrentPassword.requestFocus()
//                return@btnConfirm
//            }
//
//            user.let {
//                val userCredential = EmailAuthProvider.getCredential(it?.email!!,pass)
//                it.reauthenticate(userCredential).addOnCompleteListener { task ->
//                    when {
//                        task.isSuccessful -> {
//                            binding.cvCurrentPass.visibility = View.GONE
//                            binding.cvUpdatePass.visibility = View.VISIBLE
//                        }
//                        task.exception is FirebaseAuthInvalidCredentialsException -> {
//                            binding.edtCurrentPassword.error = "Password Salah"
//                            binding.edtCurrentPassword.requestFocus()
//                        }
//                        else -> {
//                            Toast.makeText(activity, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
//
//            binding.btnNewCancel.setOnClickListener {
//                binding.cvCurrentPass.visibility = View.GONE
//                binding.cvUpdatePass.visibility = View.GONE
//            }
//
//            binding.btnNewChange.setOnClickListener newChangePassword@{
//
//                val newPass = binding.edtNewPass.text.toString()
//                val passConfirm = binding.edtConfirmPass.text.toString()
//
//                if (newPass.isEmpty()) {
//                    binding.edtCurrentPassword.error = "Password Tidak Boleh Kosong"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//
//                if(passConfirm.isEmpty()){
//                    binding.edtCurrentPassword.error = "Ulangi Password Baru"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//
//                if (newPass.length < 6) {
//                    binding.edtCurrentPassword.error = "Password Harus Lebih dari 6 Karakter"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//
//                if (passConfirm.length < 6) {
//                    binding.edtCurrentPassword.error = "Password Tidak Sama"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//
//                if (newPass != passConfirm) {
//                    binding.edtCurrentPassword.error = "Password Tidak Sama"
//                    binding.edtCurrentPassword.requestFocus()
//                    return@newChangePassword
//                }
//
//                user?.let {
//                    user.updatePassword(newPass).addOnCompleteListener {
//                        if (it.isSuccessful){
//                            Toast.makeText(activity, "Password Berhasil diUpdate", Toast.LENGTH_SHORT).show()
//                            successLogout()
//                        } else {
//                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//
//            }
//        }
//    }

    private fun successLogout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()

        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()

        Toast.makeText(activity, "Silahkan Login Kembali", Toast.LENGTH_SHORT).show()
    }

//    private fun emailVerification() {
//        val user = auth.currentUser
//        user?.sendEmailVerification()?.addOnCompleteListener {
//            if(it.isSuccessful){
//                Toast.makeText(activity, "Email Verifikasi Telah Dikirim", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun goToCamera() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
//            activity?.packageManager?.let {
//                intent?.resolveActivity(it).also {
//                    startActivityForResult(intent, REQ_CAM)
//                }
//            }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQ_CAM && resultCode == RESULT_OK) {
//            val imgBitmap = data?.extras?.get("data") as Bitmap
//            uploadImgToFirebase(imgBitmap)
//        }
//    }
//
//    private fun uploadImgToFirebase(imgBitmap: Bitmap) {
//        val baos = ByteArrayOutputStream()
//        val ref = FirebaseStorage.getInstance().reference.child("img_user/${FirebaseAuth.getInstance().currentUser?.email}")
//        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//
//        val img = baos.toByteArray()
//        ref.putBytes(img)
//            .addOnCompleteListener{
//                if (it.isSuccessful){
//                    ref.downloadUrl.addOnCompleteListener { Task->
//                        Task.result.let{ Uri ->
//                            imgUri = Uri
//                            binding.cviUser.setImageBitmap(imgBitmap)
//                        }
//                    }
//                }
//            }
//    }

    private fun btnLogout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(context,LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    companion object{
        const val REQ_CAM = 100
    }
}
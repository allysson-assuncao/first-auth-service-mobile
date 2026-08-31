package com.example.authmobile

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var profileImage: ImageView
    private lateinit var nameInput: EditText
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var termsCheckBox: CheckBox
    private lateinit var termsTextView: TextView
    private lateinit var registerButton: Button
    private lateinit var alreadyHaveAccountText: TextView

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            profileImage.setImageURI(it)
            profileImage.setPadding(0, 0, 0, 0)
            profileImage.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        profileImage = findViewById(R.id.profile_image)
        nameInput = findViewById(R.id.name_input)
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        termsCheckBox = findViewById(R.id.terms_checkbox)
        termsTextView = findViewById(R.id.terms_text)
        registerButton = findViewById(R.id.register_button)
        alreadyHaveAccountText = findViewById(R.id.already_have_account_text)

        setupProfileImagePicker()
        setupTermsAndConditions()
        setupAlreadyHaveAccountLink()

        registerButton.setOnClickListener {
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupProfileImagePicker() {
        profileImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }

    private fun setupTermsAndConditions() {
        val fullText = "Eu li e aceito os Termos de Uso"
        val targetText = "Termos de Uso"
        val spannable = SpannableString(fullText)
        val startIndex = fullText.indexOf(targetText)
        val endIndex = startIndex + targetText.length

        if (startIndex >= 0) {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    showTermsDialog()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = Color.parseColor("#03A9F4")
                    ds.isUnderlineText = true
                }
            }
            spannable.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        termsTextView.text = spannable
        termsTextView.movementMethod = LinkMovementMethod.getInstance()

        termsCheckBox.setOnCheckedChangeListener { _, isChecked ->
            registerButton.isEnabled = isChecked
        }
    }

    private fun showTermsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Termos de Uso")
            .setMessage(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum finibus quam ut sem hendrerit, at facilisis dolor consectetur. " +
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.\n\n" +
                "Nullam id massa varius, eleifend nunc in, sollicitudin nisl. " +
                "Donec ac ex a justo facilisis viverra non et felis. " +
                "Fusce et dui pretium, scelerisque leo id, placerat elit."
            )
            .setPositiveButton("Fechar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun setupAlreadyHaveAccountLink() {
        val fullText = "Já possui uma conta? Login"
        val targetText = "Login"
        val spannable = SpannableString(fullText)
        val startIndex = fullText.indexOf(targetText)
        val endIndex = startIndex + targetText.length

        if (startIndex >= 0) {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = Color.parseColor("#03A9F4")
                    ds.isUnderlineText = true
                    ds.isFakeBoldText = true
                }
            }
            spannable.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        alreadyHaveAccountText.text = spannable
        alreadyHaveAccountText.movementMethod = LinkMovementMethod.getInstance()
    }
}
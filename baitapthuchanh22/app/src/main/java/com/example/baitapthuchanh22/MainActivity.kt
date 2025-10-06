package com.example.baitapthuchanh22

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var tvError: TextView
    private lateinit var btnKiemTra: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Xử lý WindowInsets cho edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Khởi tạo views
        edtEmail = findViewById(R.id.edtEmail)
        tvError = findViewById(R.id.tvError)
        btnKiemTra = findViewById(R.id.btnKiemTra)

        btnKiemTra.setOnClickListener {
            validateEmail()
        }
    }

    private fun validateEmail() {
        val email = edtEmail.text.toString().trim()

        when {
            email.isEmpty() -> {
                showError("Email không hợp lệ")
            }
            !email.contains("@") -> {
                showError("Email không đúng định dạng")
            }
            else -> {
                showError("Email hợp lệ")
            }
        }
    }

    private fun showError(message: String) {
        tvError.text = message
        tvError.visibility = View.VISIBLE
    }

    private fun hideError() {
        tvError.text = ""
        tvError.visibility = View.GONE
    }
}
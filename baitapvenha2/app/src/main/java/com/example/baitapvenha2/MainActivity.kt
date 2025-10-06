package com.example.baitapvenha2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var buttonCheck: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các thành phần
        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        buttonCheck = findViewById(R.id.buttonCheck)

        // Xử lý sự kiện click nút Kiểm tra
        buttonCheck.setOnClickListener {
            checkAge()
        }
    }

    private fun checkAge() {
        // Lấy dữ liệu từ EditText
        val name = editTextName.text.toString().trim()
        val ageStr = editTextAge.text.toString().trim()

        // Kiểm tra dữ liệu đầu vào
        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập họ và tên!", Toast.LENGTH_SHORT).show()
            return
        }

        if (ageStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tuổi!", Toast.LENGTH_SHORT).show()
            return
        }

        // Chuyển đổi tuổi sang số
        val age: Int = try {
            ageStr.toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Tuổi không hợp lệ!", Toast.LENGTH_SHORT).show()
            return
        }

        // Kiểm tra tuổi hợp lệ
        if (age < 0 || age > 150) {
            Toast.makeText(this, "Tuổi phải từ 0 đến 150!", Toast.LENGTH_SHORT).show()
            return
        }

        // Phân loại theo độ tuổi
        val category = categorizeAge(age)

        // Hiển thị kết quả
        showResult(name, age, category)
    }

    private fun categorizeAge(age: Int): String {
        return when {
            age > 65 -> "Người già"
            age in 6..65 -> "Người lớn"
            age in 2..6 -> "Trẻ em"
            else -> "Em bé" // age < 2
        }
    }

    private fun showResult(name: String, age: Int, category: String) {
        val message = """
            Họ và tên: $name
            Tuổi: $age
            Phân loại: $category
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Kết quả kiểm tra")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
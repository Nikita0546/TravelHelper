package com.example.travelhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val etCardNumber: EditText = findViewById(R.id.etCardNumber)
        val etExpirationDate: EditText = findViewById(R.id.etExpirationDate)
        val etCVV: EditText = findViewById(R.id.etCVV)
        val btnPay: Button = findViewById(R.id.btnPay)

        btnPay.setOnClickListener {
            val cardNumber = etCardNumber.text.toString()
            val expirationDate = etExpirationDate.text.toString()
            val cvv = etCVV.text.toString()

            if (validatePayment(cardNumber, expirationDate, cvv)) {
                processPayment(cardNumber, expirationDate, cvv)
            } else {
                Toast.makeText(this, "Неверные данные карты", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validatePayment(cardNumber: String, expirationDate: String, cvv: String): Boolean {
        // Здесь должна быть логика валидации данных карты
        return true // Возвращаем true для примера
    }

    private fun processPayment(cardNumber: String, expirationDate: String, cvv: String) {
        // Здесь должна быть логика обработки платежа, например, интеграция с платежным шлюзом
        Toast.makeText(this, "Эта карта не соответствует требованиям", Toast.LENGTH_LONG).show()
    }
}
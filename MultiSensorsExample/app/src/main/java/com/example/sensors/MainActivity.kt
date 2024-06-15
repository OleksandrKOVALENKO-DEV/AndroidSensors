package com.example.sensors

// Імпорт класу AppCompatActivity для створення активностей з підтримкою сумісності з різними версіями Android
import androidx.appcompat.app.AppCompatActivity
// Імпорт класу Bundle для передачі даних між активностями при їх створенні
import android.os.Bundle
// Імпорт класу Intent для роботи з інтенціями, які використовуються для запуску інших активностей
import android.content.Intent
// Імпорт класу View для роботи з елементами інтерфейсу
import android.view.View
// Клас MainActivity, який успадковує клас AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Перевизначений метод onCreate, який викликається при створенні активності
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлення макету для цієї активності
        setContentView(R.layout.activity_main)
    }

    // Метод для обробки натискання кнопки для запуску активності з акалерометром
    fun start_acselerometr(view:View){
        // Створення нового інтенту для переходу на активність з акалерометром
        val my_intent = Intent(this,Acselerometr::class.java)
        // Запуск нової активності
        startActivity(my_intent)
        // Завершення поточної активності
        finish()
    }

    // Метод для обробки натискання кнопки для запуску активності з датчиком освітлення
    fun start_light_sensor(view:View){
        // Створення нового інтенту для переходу на активність з датчиком освітлення
        val my_intent = Intent(this,Light_sensor::class.java)
        // Запуск нової активності
        startActivity(my_intent)
        // Завершення поточної активності
        finish()
    }

    // Метод для обробки натискання кнопки для запуску активності з компасом
    fun start_compass(view:View){
        // Створення нового інтенту для переходу на активність з компасом
        val my_intent = Intent(this,Magnetic_sensor::class.java)
        // Запуск нової активності
        startActivity(my_intent)
        // Завершення поточної активності
        finish()
    }

    // Метод для обробки натискання кнопки для запуску активності з гіроскопом
    fun start_gyroscope(view:View){
        // Створення нового інтенту для переходу на активність з гіроскопом
        val my_intent = Intent(this,Gyroscope::class.java)
        // Запуск нової активності
        startActivity(my_intent)
        // Завершення поточної активності
        finish()
    }

    // Метод для обробки натискання кнопки для запуску активності з датчиком наближення
    fun start_proximity(view:View){
        // Створення нового інтенту для переходу на активність з датчиком наближення
        val my_intent = Intent(this,Proximity_sensor::class.java)
        // Запуск нової активності
        startActivity(my_intent)
        // Завершення поточної активності
        finish()
    }
}

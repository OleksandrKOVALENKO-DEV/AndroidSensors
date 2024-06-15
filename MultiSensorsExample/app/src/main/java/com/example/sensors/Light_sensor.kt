package com.example.sensors

// Імпорт класу Context для отримання доступу до системних сервісів, таких як сенсори
import android.content.Context
// Імпорт класу Intent для роботи з інтенціями, які використовуються для запуску інших активностей
import android.content.Intent
// Імпорт класу Sensor для роботи з апаратними сенсорами пристрою
import android.hardware.Sensor
// Імпорт класу SensorEvent, який містить інформацію про подію сенсора
import android.hardware.SensorEvent
// Імпорт інтерфейсу SensorEventListener, який використовується для отримання подій від сенсора
import android.hardware.SensorEventListener
// Імпорт класу SensorManager для доступу до сенсорів і керування ними
import android.hardware.SensorManager
// Імпорт класу AppCompatActivity для створення активностей з підтримкою сумісності з різними версіями Android
import androidx.appcompat.app.AppCompatActivity
// Імпорт класу Bundle для передачі даних між активностями при їх створенні
import android.os.Bundle
// Імпорт класу View для роботи з елементами інтерфейсу
import android.view.View
// Імпорт класу TextView для відображення тексту в інтерфейсі користувача
import android.widget.TextView

// Клас Light_sensor, який успадковує клас AppCompatActivity
class Light_sensor : AppCompatActivity() {

    // Оголошення змінної sensor_manager для керування сенсорами
    lateinit var sensor_manager: SensorManager

    // Перевизначений метод onCreate, який викликається при створенні активності
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлення макету для цієї активності
        setContentView(R.layout.activity_light_sensor)

        // Пошук TextView у макеті за ID для відображення даних сенсора
        val text_light_data: TextView = findViewById(R.id.light_data)

        // Ініціалізація sensor_manager для отримання доступу до сервісів сенсорів
        sensor_manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Отримання об'єкта сенсора освітленості з доступних сенсорів
        val sensor = sensor_manager.getDefaultSensor(Sensor.TYPE_LIGHT)

        // Створення об'єкта sensor_listener для обробки подій сенсора
        val sensor_listener = object: SensorEventListener {

            // Метод, який викликається при зміні значень сенсора
            override fun onSensorChanged(event: SensorEvent?) {
                val value = event?.values
                // Формування рядка зі значенням освітленості
                val sensor_data = "${value?.get(0)} Люкс"
                // Оновлення тексту у TextView
                text_light_data.text = sensor_data
            }

            // Метод, який викликається при зміні точності сенсора (не використовується в даному випадку)
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        // Реєстрація слухача для отримання подій від сенсора з певною частотою оновлення
        sensor_manager.registerListener(sensor_listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    // Метод для обробки натискання кнопки "Назад"
    fun back(view: View){
        // Створення нового інтенту для переходу на MainActivity
        val backIntent = Intent(this,MainActivity::class.java)
        // Запуск нової активності
        startActivity(backIntent)
        // Завершення поточної активності
        finish()
    }
}

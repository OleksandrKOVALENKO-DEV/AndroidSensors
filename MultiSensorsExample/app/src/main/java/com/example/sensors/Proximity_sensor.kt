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
// Імпорт класу Bundle для передачі даних між активностями при їх створенні
import android.os.Bundle
// Імпорт класу Button для роботи з кнопками в інтерфейсі
import android.widget.Button
// Імпорт класу TextView для відображення тексту в інтерфейсі користувача
import android.widget.TextView
// Імпорт класу View для роботи з елементами інтерфейсу
import android.view.View
// Імпорт класу AppCompatActivity для створення активностей з підтримкою сумісності з різними версіями Android
import androidx.appcompat.app.AppCompatActivity

// Клас Proximity_sensor, який успадковує клас AppCompatActivity
class Proximity_sensor : AppCompatActivity() {

    // Оголошення приватних змінних для керування датчиком наближення
    private lateinit var sensor_manager: SensorManager
    private var sensor_listener: SensorEventListener? = null
    private var my_sensor: Sensor? = null

    // Перевизначений метод onCreate, який викликається при створенні активності
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлення макету для цієї активності
        setContentView(R.layout.activity_proximity_sensor)

        // Отримання посилань на TextView та кнопку з макету
        val text_step_data: TextView = findViewById(R.id.proximity_data)
        val resetButton: Button = findViewById(R.id.reset_button)

        // Ініціалізація sensor_manager для отримання доступу до сервісів сенсорів
        sensor_manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // Отримання об'єкта датчика наближення
        my_sensor = sensor_manager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        // Створення об'єкта sensor_listener для обробки подій датчика
        sensor_listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                val value = event?.values
                // Перевірка значень датчика та відображення відповідного тексту
                if (value?.get(0) == 0f) {
                    resetButton.visibility = Button.VISIBLE
                    text_step_data.text = "Датчик закрили"
                } else {
                    resetButton.visibility = Button.INVISIBLE
                    text_step_data.text = "Датчик відкритий"
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        // Реєстрація слухача для отримання подій від датчика наближення
        sensor_manager.registerListener(sensor_listener, my_sensor, SensorManager.SENSOR_DELAY_FASTEST)

        // Обробник натискання кнопки resetButton
        resetButton.setOnClickListener {
            // Виклик методу для скидання датчика
            resetSensor()
            // Зміна видимості кнопки resetButton на невидиму
            resetButton.visibility = Button.INVISIBLE
        }
    }

    // Перевизначений метод onPause, який викликається при призупиненні активності
    override fun onPause() {
        super.onPause()
        // Відміна реєстрації слухача при призупиненні активності
        sensor_manager.unregisterListener(sensor_listener)
    }

    // Метод для скидання датчика наближення
    private fun resetSensor() {
        // Перевірка, чи існують об'єкти sensor_listener та my_sensor
        sensor_listener?.let { sensor_listener ->
            my_sensor?.let { sensor ->
                // Відміна реєстрації слухача з подальшою знову реєстрацією
                sensor_manager.unregisterListener(sensor_listener, sensor)
                sensor_manager.registerListener(sensor_listener, sensor, SensorManager.SENSOR_DELAY_FASTEST)
            }
        }
    }

    // Метод для обробки натискання кнопки "Назад"
    fun back(view: View){
        // Створення нового інтенту для переходу на MainActivity
        val backIntent = Intent(this, MainActivity::class.java)
        // Запуск нової активності
        startActivity(backIntent)
        // Завершення поточної активності
        finish()
    }
}

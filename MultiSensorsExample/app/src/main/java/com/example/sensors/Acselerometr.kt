package com.example.sensors
// Підключення бібліотек
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
// Імпорт класу Handler для обробки і відправки повідомлень і runnable об'єктів
import android.os.Handler
// Імпорт класу Looper для управління циклом подій потоку
import android.os.Looper
// Імпорт класу View для роботи з елементами інтерфейсу
import android.view.View
// Імпорт класу TextView для відображення тексту в інтерфейсі користувача
import android.widget.TextView
// Імпорт класу AppCompatActivity для створення активностей з підтримкою сумісності з різними версіями Android
import androidx.appcompat.app.AppCompatActivity

// Оголошення класу Acselerometr який успадковує клас AppCompatActivity
class Acselerometr : AppCompatActivity() {
    // Оголошення змінної для менеджера сенсорів
    private lateinit var sensorManager: SensorManager

    // Оголошення змінної для сенсора акселерометра
    private lateinit var accelerometer: Sensor

    // Оголошення змінної для слухача подій сенсора
    private lateinit var sensorListener: SensorEventListener

    // Оголошення змінної для текстового поля, яке відображатиме дані сенсора
    private lateinit var sensorDataTextView: TextView

    // Оголошення обробника для виконання операцій у головному потоці
    private val handler = Handler(Looper.getMainLooper())

    // Метод, який виконується при створенні активності
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлення макету для цієї активності
        setContentView(R.layout.activity_acselerometr)

        // Пошук TextView у макеті за ID для відображення даних сенсора
        sensorDataTextView = findViewById(R.id.acselerometr_data)

        // Ініціалізація менеджера сенсорів
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Отримання об'єкта акселерометра з доступних сенсорів
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!

        // Створення слухача подій для обробки змін у даних сенсора
        sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                // Отримання значень по осях X, Y, Z
                val value = event.values
                // Форматування значень з точністю до двох знаків після коми
                val formattedX = "%.2f".format(value[0])
                val formattedY = "%.2f".format(value[1])
                val formattedZ = "%.2f".format(value[2])
                // Створення рядка для відображення значень акселерометра
                val sensorData = "\nX: $formattedX м/с\u00B2 \nY: $formattedY м/с\u00B2 \nZ: $formattedZ м/с\u00B2 "
                // Оновлення тексту у TextView
                updateSensorData(sensorData)
            }

            // Обробка змін точності сенсора (не використовується в даному випадку)
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }

        // Реєстрація слухача для отримання подій від акселерометра з певною частотою оновлення
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    // Метод для оновлення даних сенсора в TextView
    private fun updateSensorData(sensorData: String) {
        // Оновлення тексту у головному потоці
        handler.post {
            sensorDataTextView.text = sensorData
        }
        // Додавання затримки (не виконує жодних дій в даному випадку)
        handler.postDelayed({ }, 10000)
    }

    // Метод, який викликається при знищенні активності
    override fun onDestroy() {
        super.onDestroy()
        // Від'єднання слухача від сенсора
        sensorManager.unregisterListener(sensorListener)
        // Видалення всіх запланованих повідомлень і runnable
        handler.removeCallbacksAndMessages(null)
    }

    // Метод для обробки натискання кнопки "Назад"
    fun back(view: View) {
        // Створення нового інтенту для переходу на MainActivity
        val backIntent = Intent(this, MainActivity::class.java)
        // Запуск нової активності
        startActivity(backIntent)
        // Завершення поточної активності
        finish()
    }
}

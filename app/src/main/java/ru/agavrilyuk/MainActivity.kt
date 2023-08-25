package ru.agavrilyuk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import ru.agavrilyuk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDb(this)
        db.itemDao().getAll().asLiveData().observe(this){ itemList ->
            binding.textView2.text=""
            itemList.forEach {
                val text = "id: ${it.id}  Name: ${it.name}\n"
                binding.textView2.append(text)
            }
        }
        binding.btSave.setOnClickListener {
            val item = Item(null,
                binding.inputName.text.toString()
            )
            Thread{
                db.itemDao().insertItem(item)
            }.start()

        }
    }
}
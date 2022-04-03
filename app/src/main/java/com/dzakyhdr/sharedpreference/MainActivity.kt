package com.dzakyhdr.sharedpreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dzakyhdr.sharedpreference.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            val id = binding.etInputId.text.toString().toInt()
            val name = binding.etInputName.text.toString()
            val editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Snackbar.make(binding.root, "Data Behasil Ditambahkan", Snackbar.LENGTH_LONG).show()
        }

        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "Nama Tidak Ada")
            if (sharedIdValue == 0 && sharedNameValue == "Nama Tidak Ada") {
                binding.apply {
                    tvShowId.text = sharedIdValue.toString()
                    tvShowName.text = sharedNameValue
                    Toast.makeText(this@MainActivity, "Data View Kosong", Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.tvShowId.text = sharedIdValue.toString()
                binding.tvShowName.text = sharedNameValue
                Toast.makeText(this, "data view ditampilkan", Toast.LENGTH_SHORT).show()

            }
        }

        binding.btnClear.setOnClickListener {
            val editPref = sharedPreferences.edit()
            editPref.clear()
            editPref.apply()
            binding.tvShowId.text = ""
            binding.tvShowName.text = ""
            Snackbar.make(binding.root, "Data Berhasil Dihapus", Snackbar.LENGTH_LONG).show()
        }
    }
}
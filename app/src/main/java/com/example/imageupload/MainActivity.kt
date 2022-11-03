package com.example.imageupload

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startFileChooser()
        }

        button2.setOnClickListener {
            Toast.makeText(this, "Error: no upload server found\n" +
                    "Please check the source code.",Toast.LENGTH_LONG).show()
        }
    }

    private fun startFileChooser() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"Choose Picture"), 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==111 && resultCode == Activity.RESULT_OK && data != null) {
            var filepath = data.data!!
            var bitmap= MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            imageView.setImageBitmap(bitmap)
        }
        if (requestCode!=111 && resultCode != Activity.RESULT_OK && data != null) {
            val errorToast = Toast.makeText(
                this,
                "Note (for developers): If you find any errors or bugs while testing, please raise a PR at https://github.com/Xhyperi0nX/photodrive-app",
                Toast.LENGTH_SHORT
            )
            errorToast.show()
        }
    }
}
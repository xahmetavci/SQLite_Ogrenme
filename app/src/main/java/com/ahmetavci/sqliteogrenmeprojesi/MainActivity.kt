package com.ahmetavci.sqliteogrenmeprojesi

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //try catch

        try { //bütün yazıcağımız SQLite kodlarını burada yacacağız

            val veritabani = this.openOrCreateDatabase("Urunler", Context.MODE_PRIVATE,null) //Burada yazdığımız kod satırının amacı, SQL/SQLite veri tabanı dosyası oluşturmak.
            veritabani.execSQL("CREATE TABLE IF NOT EXISTS urunler (id INTEGER PRIMARY KEY, isim VARCHAR, fiyat INT)")  //column(sütun) larımız belli id, isim ve fiyat. Şimdi sırada bu column lara row(sıra) ekleyeceğiz.

            /*
            veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Ayakkabi',200)")
            veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Tshirt',100)")
            veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Sweatshirt',150)")
            veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Mont',200)")
            veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Pantolon',150)")
            veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Corap',25)")
            veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Boxer',50)")
            veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Bere',75)")
            */

            //veritabani.execSQL("DELETE FROM urunler WHERE id = 5") //veri tabanındaki tablodaki id si 5 olan veriyi sildik.
            //veritabani.execSQL("UPDATE urunler SET fiyat = 350 WHERE isim = 'Ayakkabi'")
            //veritabani.execSQL("UPDATE urunler SET isim = 'Sneakers' WHERE isim = 'Ayakkabi'")
            veritabani.execSQL("UPDATE urunler SET isim = 'Bileklik' WHERE id = 6")


            val cursor = veritabani.rawQuery("SELECT * FROM urunler",null)
            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE id = 3", null)
            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE isim LIKE '%t'",null)

            val idColumnIndex = cursor.getColumnIndex("id")
            val isimColumnIndex = cursor.getColumnIndex("isim")
            val fiyatColumnIndex = cursor.getColumnIndex("fiyat")

            while (cursor.moveToNext()){
                println("ID : ${cursor.getInt(idColumnIndex)}")
                println("Isim : ${cursor.getString(isimColumnIndex)}")
                println("Fiyat : ${cursor.getInt(fiyatColumnIndex)}")
            }
            cursor.close()





        } catch (e : Exception){
            e.printStackTrace() //yakaladığı bütün hataları loglara(logcat) yazdırır.
        }

    }
}
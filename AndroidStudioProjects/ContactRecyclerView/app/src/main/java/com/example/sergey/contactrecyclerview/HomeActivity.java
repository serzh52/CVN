package com.example.sergey.contactrecyclerview;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/*создаем клас Хоумактивити активити унаследованный от Эпкомпэйтактивити  с приватными глобольными переменными
* создаем метод без возвращаемого значения который создает пользовательский интерфейс
*запускаем метод родителького класса onCreate() в дополнении с кодом своего onCreate()
*подключаем xml файл разметки
* */
public class HomeActivity extends AppCompatActivity {
    private ProgressBar progress;
    private ListView nameNumber;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView contList = (ListView) findViewById(R.id.contactList);//
        adapter = new ContactsAdapter(this,R.layout.contact_item);//инициализируем адаптер
        contList.setAdapter(adapter);//


        this.nameNumber = (ListView) findViewById(R.id.contactList);//
        this.progress = (ProgressBar) findViewById(R.id.progressBar);//
        this.showContacts();//


    }

    private void showContacts() {//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            new DetailCont().execute();//
        }
    }



    @Override//
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {//
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                new DetailCont().execute();//
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();//
            }
        }
    }

    private List<Contact> getContactNames() {//
        List<Contact> contacts = new ArrayList<>();//
        ContentResolver cr = getContentResolver();//
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);//

        if (cursor!= null&&cursor.moveToFirst()) {//
            do {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));//
                //String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contact contact = new Contact(name, null);//
                contacts.add(contact);//


            } while (cursor.moveToNext());//
        }

        cursor.close();//

        return contacts;//
    }

    class DetailCont extends AsyncTask<Void, Void, List<Contact>> {//создаем класс который перемещает отображение контактов в отдельный поток унаследованный от асинктаск (на прямую исп.нельзя)

        @Override
        protected void onPreExecute() {//выполняется перед doInBackground()
            progress.setVisibility(View.VISIBLE);//перед началом выполнения задачи показывааем прогресс бар
        }

        @Override
        protected List<Contact> doInBackground(Void... params) {//основной метод в котором находятся тяжелые задачи требующие участия отдельного потока в данном случае использование метода гетконтакт неймс и массива контактов
            return getContactNames();//возвращает данные метода гетконтактнейм
        }

        @Override
        protected void onPostExecute(List<Contact> result) {//выполняется после doInBackground(),обновляет пользовательски интерфейс
            progress.setVisibility(View.INVISIBLE);//заканчивает показ прогресс бара
            adapter.addAll(result);//заполняем объектами резалт

        }


    }
}

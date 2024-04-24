package com.example.m2lgestion.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.m2lgestion.MyApplication;
import com.example.m2lgestion.entity.Role;
import com.example.m2lgestion.entity.User;

public class RoleDao {

    // Create
    public static void saveRole(Role role){
        SQLiteDatabase db =
                MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("roleName", role.getRoleName());
        db.insert("Role", null, values);
     //   db.close();
    }

    // Read
    @SuppressLint("Range")
    public static Role findRoleById(int roleId) {
        SQLiteDatabase db =
                MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.query("Role", new String[]{"id", "roleName"},
"id=?", new String[]{String.valueOf(roleId)}, null, null, null);
        Role role = null;
        if (cursor.moveToFirst()) {
            role = new Role();
            role.setId(cursor.getInt(cursor.getColumnIndex("id")));
            role.setRoleName(cursor.getString(cursor.getColumnIndex("roleName")));
        }
        cursor.close();
      //  db.close();
        return role;
    }
    // Update
    public static int updateRole(Role role) {
        SQLiteDatabase db =
                MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("roleName", role.getRoleName());
        int rowsAffected = db.update("Role", values, "id=?", new
                String[]{String.valueOf(role.getId())});
      //  db.close();
        return rowsAffected;
    }
    // Delete
    public static void deleteRole(int roleId) {
        SQLiteDatabase db =
                MyApplication.getDbHelper().getWritableDatabase();
        db.delete("Role", "id=?", new String[]{String.valueOf(roleId)});
       // db.close();
    }
    //
    @SuppressLint("Range")
    public static User findUserByEmailAndPassword(String email, String
            password) {
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        String[] columns = new String[]{"id", "email", "password", "nom",
                "prenom", "roleId"};
        String selection = "email = ? AND password = ?";
        String[] selectionArgs = new String[]{email, password};
        Cursor cursor = db.query("User", columns, selection, selectionArgs,
                null, null, null);
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            user.setNom(cursor.getString(cursor.getColumnIndex("nom")));
            user.setPrenom(cursor.getString(cursor.getColumnIndex("prenom")));
// Récupérer et associer le rôle de l&#39;utilisateur
            int roleId = cursor.getInt(cursor.getColumnIndex("roleId"));
            Role role = RoleDao.findRoleById(roleId);
            user.setRole(role);
        }
        cursor.close();
      //  db.close();
        return user;

}}
